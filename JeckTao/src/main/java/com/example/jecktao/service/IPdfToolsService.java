package com.example.jecktao.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * pdf 转换工具服务接口
 */
public interface IPdfToolsService {
    String PdfToWord(MultipartFile file, HttpServletResponse response);
}
