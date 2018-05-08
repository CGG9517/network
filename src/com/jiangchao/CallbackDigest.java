package com.jiangchao;

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
public class CallbackDigest implements Runnable {
    private String filename;
    private CallbackDigestUserInterface callback;


    public CallbackDigest(String filename, CallbackDigestUserInterface callback) {
        this.filename = filename;
        this.callback = callback;
    }

    @Override
    public void run() {

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            InputStream in = new FileInputStream(filename);
            in = new DigestInputStream(in, sha);
            while (in.read() != -1) ;
            in.close();
            // 进程的最后一步，回调receiveDigest()方法，回调方实例方法
            callback.receiveDigest(sha.digest());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
