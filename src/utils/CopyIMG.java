package utils;

import java.io.*;
public class CopyIMG{
    public static void main(String[] args)throws Exception{
        File file = new File("H:\\������ҳ\\GDPU������5.6�汾\\images\\incorruptsIMG\\ic102.jpg");
        String filename =file.getName();
        System.out.println(filename);
        if(!file.exists())
            throw new RuntimeException("�ļ�������..");
        FileInputStream fis = new FileInputStream(file);
        byte[] b = new byte[1024];
        int len = 0;
        FileOutputStream fos = new FileOutputStream("D:\\try\\Book_web\\WebContent\\images\\ic102.jpg");
        while((len=fis.read(b))!=-1){
            fos.write(b,0,len);
        }
        fos.close();
        fis.close();
    }
}
