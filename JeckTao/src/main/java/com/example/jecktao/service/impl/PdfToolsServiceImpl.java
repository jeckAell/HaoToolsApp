package com.example.jecktao.service.impl;

import com.example.jecktao.service.IPdfToolsService;
import com.example.jecktao.utils.JeckTaoExecption;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.widget.PdfPageCollection;

import java.io.File;
import java.io.IOException;

public class PdfToolsServiceImpl implements IPdfToolsService {

    /**
     * 将pdf转为word
     * @param srcPath 一个文件流
     */
    @Override
    public void PdfToWord(String srcPath) throws IOException {
        // 转换后的文件存放路径
        String desPath = srcPath.substring(0, srcPath.length()-4)+".docx";
        boolean result = false;
        try {
            // 0、判断输入的是否是pdf文件
            //第一步：判断输入的是否合法
            boolean flag = isPDFFile(srcPath);

            if (flag) {
                // 1、加载pdf
                PdfDocument pdf = new PdfDocument();
                pdf.loadFromFile(srcPath);
                PdfPageCollection num = pdf.getPages();

                pdf.saveToFile(desPath, com.spire.pdf.FileFormat.DOCX);
            } else {
                JeckTaoExecption.throwExecption("-1", "输入的不是pdf文件");
            }
        } catch (Exception e) {
            JeckTaoExecption.throwExecption("-1", "转换pdf文件错误");
        }finally {

        }
    }

    // 判断是否是pdf文件
    private boolean isPDFFile(String srcPath2) {
        File file = new File(srcPath2);
        String filename = file.getName();
        if (filename.endsWith(".pdf")) {
            return true;
        }
        return false;
    }
}
