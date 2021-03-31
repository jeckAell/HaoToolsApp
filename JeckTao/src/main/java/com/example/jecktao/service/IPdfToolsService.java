package com.example.jecktao.service;

import java.io.File;
import java.io.IOException;

/**
 * pdf 转换工具服务接口
 */
public interface IPdfToolsService {
    void PdfToWord(String  srcPath) throws IOException;
}
