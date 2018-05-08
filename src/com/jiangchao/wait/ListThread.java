package com.jiangchao.wait;

import java.io.InputStream;
import java.util.jar.Manifest;

/**
 * @class: ListThread
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/5/7
 */
public class ListThread implements Runnable {
    // 假设是这个对象
    Manifest m = new Manifest();
    private InputStream in;
    JarThread jar = new JarThread(m, in);


    @Override
    public void run() {
        synchronized (m) {
            // 等待这个对象；
            try {
                m.wait();
                // 其他线程释放m的锁，处理清单文件....
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
