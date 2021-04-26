package com.example.jecktao.service.impl;

import com.example.jecktao.service.IPdfToolsService;
import com.example.jecktao.utils.JeckTaoExecption;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.widget.PdfPageCollection;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class PdfToolsServiceImpl implements IPdfToolsService {

    /**
     * 将pdf转为word
     * @param file 一个文件流
     */
    @Override
    public String PdfToWord(MultipartFile file, HttpServletResponse response) {
        String returnFileName = "";
        if (!file.isEmpty()) {
            try {
                String filename = file.getOriginalFilename();
                String srcPath = "D:/" + LocalDate.now() + "/" + filename;

                File newFile = new File(srcPath);

                if (!newFile.getParentFile().exists()) {
                    newFile.getParentFile().mkdir();
                }
                // 保存文件到本地
                file.transferTo(newFile);

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
                        if (num.getCount() > 10) {
                            JeckTaoExecption.throwExecption("-1", "sorry，pdf大于10页我不想法解析");
                        }
                        pdf.saveToFile(desPath, com.spire.pdf.FileFormat.DOCX);
                        returnFileName = downFile(desPath, response);
                    } else {
                        JeckTaoExecption.throwExecption("-1", "输入的不是pdf文件");
                    }
                } catch (Exception e) {
                    JeckTaoExecption.throwExecption("-1", "转换pdf文件错误");
                }finally {

                }
            } catch (FileNotFoundException e) {
                JeckTaoExecption.throwExecption("-1", "上传文件失败");
            } catch (IOException e) {
                JeckTaoExecption.throwExecption("-1", "上传文件失败");
            }
        } else {
            JeckTaoExecption.throwExecption("-1", "上传文件失败");
        }

        return returnFileName;
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

    // 下载文件
    private String downFile(String filePath, HttpServletResponse response) {
        String downFileName = "";
        if (filePath != null) {
            File file = new File(filePath);
            downFileName = new String(file.getName().getBytes(StandardCharsets.UTF_8));

            if (file.exists()) {
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition","attachment;fileName="+downFileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;

                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while(i != -1){
                        os.write(buffer,0,i);
                        i = bis.read(buffer);
                    }
                } catch (FileNotFoundException e) {
                    JeckTaoExecption.throwExecption("-1", "下载失败");
                } catch (IOException e) {
                    JeckTaoExecption.throwExecption("-1", "下载失败");
                } finally {
                    if (bis != null){
                        try{
                            bis.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (fis != null){
                        try{
                            fis.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return downFileName;
    }

    public static String generateUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","").substring(0,32);
        return uuid;
    }
}
