package com.jiangchao.executor;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * @class: GZipRunnable
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/5/7
 */
public class GZipRunnable implements Runnable {
    private final File input;

    public GZipRunnable(File input) {
        this.input = input;
    }

    @Override
    public void run() {
        // 避免压缩已经压缩的文件
        String filename = input.getName();
        if (!filename.endsWith(".gz")) {
            File file = new File(input.getParent(), filename + ".gz");
            // 不覆盖已经存在的文件
            if (!file.exists()) {
                try (InputStream in = new BufferedInputStream(new FileInputStream(input));
                     OutputStream out = new BufferedOutputStream(new GZIPOutputStream(
                                        new FileOutputStream(file)));)
                {
                    int c;
                    while ((c = in.read())!=-1) out.write(c);
                    out.flush();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
