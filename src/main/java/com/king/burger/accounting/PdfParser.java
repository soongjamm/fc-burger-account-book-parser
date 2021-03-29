package com.king.burger.accounting;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfParser {

    private AccountDayDao accountDayDao;

    @Autowired
    public void setAccountDayDao(AccountDayDao accountDayDao) {
        this.accountDayDao = accountDayDao;
    }

    @SneakyThrows
    public List<AccountDay> parse(MultipartFile pdfFile) {
        String text = extractPdfText(pdfFile);
        ArrayList<String> accountDayByLine = new ArrayList<>();
        ArrayList<AccountDay> accountDays = new ArrayList<>();
        String[] split = text.split("\n");

        if (!text.startsWith("축구공금")) {
            throw new IllegalArgumentException("축구공금 파일 아님");
        }

        for (int i = 1; i < split.length; i++) { // 중간에 '=' 로 시작하는 행을 만나면 거기까지 한 묶음으로 해서 AccountDay 를 처리한다.
            accountDayByLine.add(split[i]);
            if (!isAccountDayTitle(split[i])) {
                continue;
            }
            AccountDay accountDay = AccountDayFactory.makeAccountDay(accountDayByLine);
            if (accountDay.getDate() == null) {
                if (accountDays.size()==0) {
                    accountDay.setDate(LocalDate.MIN);
                } else {
                    accountDay.setDate(accountDays.get(accountDays.size()-1).getDate().plusDays(1));
                }
            }
            accountDays.add(accountDay);
            accountDayByLine.clear();
        }
        return accountDayDao.saveAll(accountDays);
    }

    private boolean isAccountDayTitle(String s) {
        return s.startsWith(AccountDayFactory.TITLE_DELIMITER);
    }

    private String extractPdfText(MultipartFile pdfFile) throws IOException {
        InputStream source = pdfFile.getInputStream();
        String text = "";
        try (PDDocument pdfDoc = PDDocument.load(source)) {
            PDFTextStripper stripper = new PDFTextStripper();
            text = stripper.getText(pdfDoc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
