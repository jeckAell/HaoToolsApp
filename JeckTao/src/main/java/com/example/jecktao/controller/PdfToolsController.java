package com.example.jecktao.controller;

import com.example.jecktao.service.IPdfToolsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/jecktao/pdfTools")
public class PdfToolsController {
    @Autowired
    private IPdfToolsService pdfToolsService;

    @ApiOperation("pdf转word")
    @ResponseBody
    @PostMapping("/pdfToword")
    public void pdfToword(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        pdfToolsService.PdfToWord(file, response);
    }
}
