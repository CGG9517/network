package com.jiangchao.executor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @class: GZipThread
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/5/7
 */
public class GZipThread {
    public final static int THREAD_COCUNT = 4;

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_COCUNT);

        for (String filename : args){
            File file = new File(filename);
            if (file.exists()){
                File[] fileList;
                if (file.isDirectory()){
                    // 不做递归处理
                    fileList = file.listFiles();
                } else {
                    fileList = new File[]{file};
                }

                for (File f : fileList){
                    GZipRunnable g = new GZipRunnable(f);
                    service.submit(g);
                }

            }
        }

        /*
         * Initiates an orderly shutdown in which previously submitted
         * tasks are executed, but no new tasks will be accepted.
         * Invocation has no additional effect if already shut down.
         */
        service.shutdown();
    }
}
