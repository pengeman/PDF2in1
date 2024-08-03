package org.peng.pdf2in1;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.io.RandomAccessStreamCache;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PDFmerger {

    /**
     * pdf合并拼接
     *
     * @param files      文件列表
     * @param targetPath 合并到
     * @return
     * @throws IOException
     * @Title:mulFile2One
     * @Description: TODO
     */
    public static File mulFile2One(List<File> files, String targetPath) throws IOException {
        // pdf合并工具类
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        for (File f : files) {
            if (f.exists() && f.isFile()) {
                // 循环添加要合并的pdf
                mergePdf.addSource(f);
            }
        }
        // 设置合并生成pdf文件名称
        mergePdf.setDestinationFileName(targetPath);
        // 合并pdf
        //mergePdf.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        mergePdf.mergeDocuments(new RandomAccessStreamCache.StreamCacheCreateFunction() {
            @Override
            public RandomAccessStreamCache create() throws IOException {
                return null;
            }
        });
        return new File(targetPath);
    }

    public static void main(String[] args) throws IOException {
        List files = new ArrayList();
        files.add(new File("/tmp/f1.pdf"));
        files.add(new File("/tmp/f2.pdf"));
        System.out.println("PDFmerger...");
        File f = mulFile2One(files, "/tmp/mul2one.pdf");
        System.out.println(f.length());
    }
}

