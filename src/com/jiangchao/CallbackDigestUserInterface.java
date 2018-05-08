package com.jiangchao;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;

/**
 * @class: CallbackDigestUserInterface
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/5/6
 */
public class CallbackDigestUserInterface {
   /* // 被子线程回调的静态方法
    public static void receiveDigest(byte[] digest, String filename){
        System.out.println(filename +
                ": " + DatatypeConverter.printHexBinary(digest));
    }*/
    // 一个实例映射一个文件
    private String filename;
    private byte[] digest;

    public CallbackDigestUserInterface(String filename) {
        this.filename = filename;
    }

    // 实例的被回调方法
    public void receiveDigest(byte[] digest) {

        this.digest = digest;
        System.out.println(this);
    }

    @Override
    public String toString() {
        String result = filename + ": ";
        if (digest != null)
            result += DatatypeConverter.printHexBinary(digest);
        else result += "digest not available";
        return result;
    }

    public void calculateDigest() {
        synchronized (this){
            // this 是不是有可能未完全初始化？
            CallbackDigest r = new CallbackDigest(filename, this);
            Thread t = new Thread(r);
            t.start();
        }
    }

    public static void main(String[] args) {
        for (String s : args) {
            new CallbackDigestUserInterface(s).calculateDigest();
        }

    }
}
