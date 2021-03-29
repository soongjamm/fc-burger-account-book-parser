package com.king.burger.accounting;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class PdfParserController {

    private PdfParser pdfParser;
    private AccountDayDao accountDayDao;

    @Autowired
    public PdfParserController(PdfParser pdfParser) {
        this.pdfParser = pdfParser;
    }

    @GetMapping("/pdf")
    @ResponseBody
    public String parsePdf() {
        // TODO : 경로에서 가져오는 것 대신에 사용자로부터 파일을 받아야함.
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
        pdfParser.parse(text);
        List<AccountDay> all = pdfParser.getAll();
        all.forEach( x-> System.out.println(x));
        return "--";
    }
}
