package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadImgs {
	public static String uploadbookimg(String book_img) throws IOException {
      //  File file = new File("H:\\������ҳ\\GDPU������5.6�汾\\images\\incorruptsIMG\\ic102.jpg");
        File file = new File(book_img);
        String filename =file.getName();
        System.out.println(filename);
        if(!file.exists())
            throw new RuntimeException("�ļ�������..");
        FileInputStream fis = new FileInputStream(file);
        byte[] b = new byte[1024];
        int len = 0;
        FileOutputStream fos = new FileOutputStream("D:\\try\\Book_web\\WebContent\\images\\"+filename);
        while((len=fis.read(b))!=-1){
            fos.write(b,0,len);
        }
        fos.close();
        fis.close();
		return filename;
	}
}
