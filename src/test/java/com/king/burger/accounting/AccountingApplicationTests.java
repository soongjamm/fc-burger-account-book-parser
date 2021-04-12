package com.king.burger.accounting;

import com.king.burger.accounting.domain.AccountBook;
import com.king.burger.accounting.domain.AccountDay;
import com.king.burger.accounting.utility.PdfParser;
import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;

class AccountingApplicationTests {

    private static final String FILE_STARTS_WITH = "축구공금";

    @SneakyThrows
    @Test
    void 축구공금_으로_시작하는_PDF_파일을_입력하면_축구공금_다음줄부터_이루어진_스트링을_반환() {
        String text = loadPdfText();
        Method extractPdfTextAccount = getExtractPdfTextAccount();
        text = (String) extractPdfTextAccount.invoke(new PdfParser(), text);
        assertThat(text)
                .doesNotContain("축구공금")
                .doesNotStartWith("\n")
                .contains("용두그린")
        ;

    }

    @Test
    void parse() {
        PdfParser pdfParser = new PdfParser();
        AccountBook book = pdfParser.parse(loadPdfText());
        LinkedList<AccountDay> accountDays = book.getAccountDays();
        for (AccountDay accountDay : accountDays) {
            System.out.println(accountDay);
        }
    }

    @Test
    void 데이트파싱테스트() throws ParseException {
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd");
//        LocalDate date = LocalDate.parse("20/02/01", pattern);

        assertThat("03/04".matches("([0-1]?[0-9])/([0-3]?[0-9])")).isTrue();
        assertThat("2020/03/04".matches("((20)?[0-9][0-9]/[0-1]?[0-9])/([0-3]?[0-9])")).isTrue();
        assertThat("+".equals('+')).isFalse();
        System.out.println(DecimalFormat.getIntegerInstance().parse("3,0000").intValue());
    }



    private Method getExtractPdfTextAccount() throws NoSuchMethodException {
        Method extractPdfTextAccount = PdfParser.class.getDeclaredMethod("skipSingleLine", String.class);
        extractPdfTextAccount.setAccessible(true);
        return extractPdfTextAccount;
    }

    private String loadPdfText() {
        File source = new File("/Users/soongjamm/Desktop/accounting.pdf");
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
