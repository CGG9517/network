package com.jiangchao.wait;

import java.io.InputStream;
import java.util.jar.Manifest;

/**
 * @class: JarThread
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/5/7
 */
public class JarThread implements Runnable {
    Manifest theManifest;
    InputStream in;

    public JarThread(Manifest theManifest, InputStream in) {
        this.theManifest = theManifest;
        this.in = in;
    }

    @Override
    public void run() {
        synchronized (theManifest){
            // 从in流读入清单文件....
            theManifest.notify();  // 随机通知一个等待的对象
        }
    }
}
