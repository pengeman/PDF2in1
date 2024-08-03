package org.peng.pdf2in1;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFBoxgo {
    public static void main(String[] args) throws IOException {
        // 1、创建文档对象
        PDDocument doc = new PDDocument();
        // 2、添加空白页面，大小是 A4 纸那么大
        PDPage page = new PDPage(PDRectangle.A4);
//        PDPage page = new PDPage(); page.setTrimBox(new PDRectangle(0, 0, 215, 139));
        doc.addPage(page);

        // TODO 添加文本内容，指定文档对象、页面对象
        PDPageContentStream stream = new PDPageContentStream(doc, page);
        stream.beginText(); // 文本开始
        //stream.setFont(PDType1Font.TIMES_ROMAN, 14); // 设置内容流文本的字体、字体大小

        stream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 14);


        stream.newLineAtOffset(10, 10); // 设置内容流文本显示的起始坐标位置

        for (int i = 0; i < 10; i++) {
            stream.setLeading(20 + i * 2); // 设置文本的前导，也就是文本行距，不设置这个行距，文本会重叠在一起
            String content = "No fallbackFactory instance of type class coapi.factory.RemoteOperationFallbac" ;
            stream.showText(content); // 设置需要添加的文本内容，注意：中文内容写入时候，需要保证字体支持中文
            stream.newLine(); // 新增一个新行显示
        }
        stream.endText(); // 文本结束
        stream.close(); // 关闭内容流

        // 3、生成pdf文件，保存pdf文件
        // 这里会在D盘下生成一个 demo.pdf 空白的pdf文档
        doc.save("/tmp/demo.pdf");
        // 4、关闭文档流
        doc.close();
    }
}
