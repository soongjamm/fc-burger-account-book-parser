package com.king.burger.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PdfParserController {

    private PdfParser pdfParser;

    @Autowired
    public PdfParserController(PdfParser pdfParser) {
        this.pdfParser = pdfParser;
    }

    @GetMapping("/pdf")
    public String parsePdf() {
        return "pdfParser";
    }


    @PostMapping("/pdf")
    public String parsePdf(@RequestParam("pdfFile") MultipartFile pdfFile) {
        if (pdfFile.isEmpty()) {
            return "redirect:pdf";
        }
        pdfParser.parse(pdfFile);
        return "redirect:accountBook";
    }


}
