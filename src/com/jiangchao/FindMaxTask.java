package com.jiangchao;

import java.util.concurrent.Callable;

/**
 * @class: FindMaxTask
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/5/6
 */
public class FindMaxTask implements Callable<Integer> {
    private int[] data;
    private int start;
    private int end;

    public FindMaxTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++)
        {
            int x = data[i];
            if (x > max) max = x;
        }
        return max;
    }


}
