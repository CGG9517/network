package com.jiangchao.wait;

import java.util.Scanner;

/**
 * @class: Main
 * @Description: 对字符串去掉重复度3次以上的词，删掉冗余的两次
 * @Author: Jiang Chao
 * @Date: 2018/5/7
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        Main main = new Main();
        System.out.println(main.deleteRepeat(s));
    }
    /**
     * @Description:
     * @param s 我都说了退款我要退款我要退款我要退款，我都说了我要退款我要退款我要退款.
     * @return: java.lang.String
     * @Author: Jiang Chao
     * @Date: 2018/5/8
     * @Version: 1.0
     */
    public String deleteRepeat(String s){

        for (int i = 0; i < s.length(); i++ )
            for (int j = i+1; j <= s.length(); j++){
            int count = 0;
            String sub = s.substring(i, j);
            // 如果是数字，i向后移
            if (Character.isDigit(sub.charAt(0))) break;
            if (j - i > (s.length()-i)/3) break;

            int start = i;
            int end = j;
            while ( end <= s.length() && s.substring(start, end).equals(sub)){
                count++;
                start = end;
                end = end + sub.length();
            }

            if (count > 2){
                s = s.substring(0, j) + s.substring(i + sub.length() * count);
                i = j - 1;
                break;
            }

        }

        return s;
    }
}
