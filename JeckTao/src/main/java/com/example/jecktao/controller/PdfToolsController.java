package com.example.jecktao.controller;

import com.example.jecktao.service.IPdfToolsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/jecktao/pdfTools")
public class PdfToolsController {
    @Autowired
    private IPdfToolsService pdfToolsService;

    @ApiOperation("pdf转word")
    @PostMapping("/pdfToword")
    public String pdfToword(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        return pdfToolsService.PdfToWord(file, response);
    }
}
