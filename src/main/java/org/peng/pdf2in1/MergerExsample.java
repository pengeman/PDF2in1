package org.peng.pdf2in1;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MergerExsample {
    public static void merger() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("/tmp/f2.pdf"));
            document.open();

            // 设置左、右、上、下边距，单位是用户单位
            document.setMargins(20, 20, 20, 20);

            document.add(new Paragraph("This is a paragraph with custom margins."));
            document.add(new Paragraph("This is a paragraph with custom margins."));
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }}
