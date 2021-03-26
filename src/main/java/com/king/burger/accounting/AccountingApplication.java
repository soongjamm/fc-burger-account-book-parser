package com.king.burger.accounting;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class AccountingApplication {

    public static void main(String[] args) {
        String fileName = "/Users/soongjamm/Desktop/accounting.pdf";
        File source = new File(fileName);
        String text = "";
        try (PDDocument pdfDoc = PDDocument.load(source)) {
            PDFTextStripper stripper = new PDFTextStripper();
            text = stripper.getText(pdfDoc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text);
        PdfParser.parse(text);

//        SpringApplication.run(AccountingApplication.class, args);
    }

}
