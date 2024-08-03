package org.peng.pdf2in1;


import javax.swing.*;
import java.io.IOException;
import java.io.IOException;
import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, DocumentException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        System.out.printf("Hello and welcome!");

//        org.peng.pdf2in1.PDFBoxgo.main(args);

        //   org.peng.pdf2in1.PDFmerger.main(args);
//        MergerExsample.merger();
        // 验证args是否有参数
        if (args.length == 0){
            outmsg();
            return;
        }
        if (args.length != 3){
            outmsg();
            return;
        }
        String filename1 = args[0];
        String filename2 = args[1];
        String outFilename = args[2];

        File[] files = new File[2];
        String outfilePath = outFilename;
        files[0] = new File(filename1);
        files[1] = new File(filename2);

        PDFmerger3.mergePdfPage(files, outfilePath);
    }

    private static void outmsg(){
        System.out.println("用 法：\n" +
                "pdf2in1 <pdf1> <pdf2> <pdf out>\n" +
                "pdf1： 第一个pdf文件\n" +
                "pdf2： 第二个pdf文件\n" +
                "pdf out ：合并后的输出文件");
    }
}
