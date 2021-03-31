package com.king.burger.accounting.domain;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PdfParser {

    private final static String ACCOUNT_DAY_DELIMITER = "=";
    private final static String FILE_STARTS_WITH = "축구공금";


    private AccountBook accountBook;

    public PdfParser() {
        this.accountBook = new AccountBook();
    }

    @SneakyThrows
    public AccountBook parse(String pdfText) {
        List<String> pdfTexts = Arrays.asList(substringStartDelimiter(pdfText).split("\n"));
        for (int i = 0; i < pdfTexts.size(); i++) { // 중간에 '=' 로 시작하는 행을 만나면 거기까지 한 묶음으로 해서 AccountDay 를 처리한다.
            if (pdfTexts.get(i).startsWith(ACCOUNT_DAY_DELIMITER)) {
                AccountDay accountDay = AccountDayFactory.makeAccountDay(pdfTexts.subList(0, i+1));
                pdfTexts = pdfTexts.subList(i+1, pdfTexts.size()-1);
                accountBook.add(accountDay);
            }
        }

        return accountBook;
    }

    @SneakyThrows
    private String substringStartDelimiter(String pdfText) {
        if (!pdfText.startsWith(FILE_STARTS_WITH)) {
            throw new IllegalArgumentException();
        }

        return pdfText.substring(pdfText.indexOf("\n")).substring(1);
    }

}
