package com.jiangchao;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @class: MultiThreadMaxFinder
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/5/6
 */
public class MultiThreadMaxFinder {
    public static int max(int[] data) throws ExecutionException, InterruptedException {
        FindMaxTask task1 = new FindMaxTask(data, 0, data.length/2);
        FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);

        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);

        return Math.max(future1.get(), future2.get());

    }

    public static void main(String[] args) {
        int[] data = {12, 3, 4, 50, 80, 1000, 142342, 4534};
        try {
            long start = new Date().getTime();
            int max = max(data);
            long end = new Date().getTime();
            System.out.println("最大值：" + max + " 用时："+ (end - start));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            long start = new Date().getTime();
            int max = new FindMaxTask(data, 0, data.length).call();
            long end = new Date().getTime();
            System.out.println("最大值：" + max + " 用时："+ (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
