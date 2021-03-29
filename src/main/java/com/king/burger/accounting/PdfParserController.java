package com.king.burger.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
public class PdfParserController {

    private PdfParser pdfParser;

    @Autowired
    public PdfParserController(PdfParser pdfParser) {
        this.pdfParser = pdfParser;
    }

    @GetMapping
    public String pdfDone() {
        return "pdfResult";
    }


    @PostMapping("/pdf")
    @ResponseBody
    public List<AccountDay> parsePdf(@RequestParam("pdfFile") MultipartFile pdfFile) {
        return pdfParser.parse(pdfFile);
    }


}
