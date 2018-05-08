package com.jiangchao;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @class: DigestRunnable
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/5/6
 */
public class DigestRunnable2 implements Runnable {
    private String filename;
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public DigestRunnable2(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            InputStream in = new FileInputStream(filename);
            in = new DigestInputStream(in, sha);
            while (in.read() != -1);
            in.close();
            bytes =  sha.digest();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description:
     * @param args myfile.txt 经管.csv 经管2.csv
     * @return: void  NullPointerException。
     * @Author: Jiang Chao
     * @Date: 2018/5/6
     * @Version: 1.0
     */
    public static void main(String[] args) {

        for (String s : args){
            DigestRunnable2 r = new DigestRunnable2(s);
            Thread t = new Thread(r);
            t.start();
            // 使用一个判断条件, 却不会打印任何东西，因为主线程比子线程快
            byte[] b;
            if ((b = r.getBytes()) != null)
                System.out.println(s + ": " +DatatypeConverter.printHexBinary(b));
        }



    }
}
