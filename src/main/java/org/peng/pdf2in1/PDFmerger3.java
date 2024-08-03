package org.peng.pdf2in1;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 * 合并PDF页
 * 1. 读取多个PDF文件
 * 2. 将每个PDF文件的第一页合并到一个新的PDF文件中
 * 3. 新的PDF文件每页放置四个PDF文件的第一页
 * 4. 两两对称放置
 * 5. 间距50f
 * 6. 生成新的PDF文件
 * 7. 保存新的PDF文件
 * 8. 关闭文件流
 *
 */
public class PDFmerger3 {


    public static void mergePdfPage(File[] files, String outfilePath) throws IOException, DocumentException {
        final float spacing = 50f;
        Document doc = new Document();
        PdfWriter writer = null;
        PdfContentByte cb = null;

        for (int i = 1; i <= files.length; i++) {
            File file = files[i - 1];
            PdfReader reader = new PdfReader(file.getAbsolutePath());
            if (i == 1) {
                Rectangle pageSize = reader.getPageSize(1);
                //设置新的PDF文档页面大小，因为要放四个，比原文件的长宽都要大2倍，然后还加了个50f的调整间距
                doc.setPageSize(new RectangleReadOnly(pageSize.getWidth()  + spacing, pageSize.getHeight() * 2 + spacing));
                //doc.setPageSize(new RectangleReadOnly(420f,297f));
                doc.setMargins(0, 0, 0, 0);
                File newFile = new File(outfilePath);
                if (newFile.exists())
                    newFile.delete();
                writer = PdfWriter.getInstance(doc, Files.newOutputStream(newFile.toPath()));
                doc.open();
                cb = writer.getDirectContent();
            }
            //只取读取文档的第一页（若要读取所有页，这里加循环每页,且i的迭代逻辑要改下，当前业务先取第一页）
            PdfImportedPage pdfpage = writer.getImportedPage(reader, 1); // page #1
            float documentWidth = doc.getPageSize().getWidth();
            float documentHeight = doc.getPageSize().getHeight();
            float pageWidth = pdfpage.getWidth();
            float pageHeight = pdfpage.getHeight();
            //是否每页第四个文件
            float four = i % 4;
            //是否每页偶数文件
            float two = i % 2;
            float offsetY = 0f;
            //仅在1、5、9。。。个文件时创建新的page
            if (four == 1) {
                doc.newPage();
            }
            //仅在每页第一个与第二个文件设置y轴为当前页高的值(感觉坐标是从左下角开始的，往上为y,往右为x)
//            if (four == 1 || (two == 0 && four != 0))
//                offsetY = pdfpage.getHeight();
            if (i == 1){
                offsetY = documentHeight;
            }
            if (i == 2){
                offsetY =10;
            }
            float widthScale = documentWidth / pageWidth;
            float heightScale = documentHeight / pageHeight;
            float scale = Math.min(widthScale, heightScale);
            //float offsetX = (documentWidth - (pageWidth * scale)) / 2;
            float offsetX = 0f;
            //偶数页放到右边
            if (two == 0) {
                //offsetX += documentWidth;
                offsetX = 0;
            }
            //cb.addTemplate(pdfpage, scale, 0, 0, scale, offsetX, offsetY);
            System.out.println("offsetY : " + offsetY);
//            cb.addTemplate(pdfpage, 10, 0, 0, 0, offsetX, offsetY);
            cb.addTemplate(pdfpage,offsetX,offsetY/2);
        }
        doc.close();
    }
}