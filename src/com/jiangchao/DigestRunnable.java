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
public class DigestRunnable implements Runnable {
    private String filename;

    public DigestRunnable(String filename) {
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
            byte[] bytes =  sha.digest();

            System.out.println(filename + ": " +DatatypeConverter.printHexBinary(bytes));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (String s : args){
            Thread t = new Thread(new DigestRunnable(s));
            t.start();
        }

    }
}
