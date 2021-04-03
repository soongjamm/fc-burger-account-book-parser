package com.king.burger.accounting.utility;

import com.king.burger.accounting.domain.AccountBook;
import com.king.burger.accounting.domain.AccountDayFactory;
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
        List<String> pdfTexts = Arrays.asList(skipSingleLine(pdfText).split("\n"));
        int firstLineIndex = 0;

        for (int i = 0; i < pdfTexts.size(); i++) {
            if (pdfTexts.get(i).startsWith(ACCOUNT_DAY_DELIMITER)) {
                List<String> targetLines = pdfTexts.subList(firstLineIndex, i + 1);
                accountBook.add(AccountDayFactory.create(targetLines, accountBook.getLast()));
                firstLineIndex = i + 1;
            }
        }
        return accountBook;
    }

    @SneakyThrows
    private String skipSingleLine(String pdfText) {
        if (!pdfText.startsWith(FILE_STARTS_WITH)) {
            throw new IllegalArgumentException();
        }

        return pdfText.substring(pdfText.indexOf("\n")).substring(1);
    }

}
