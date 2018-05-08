package com.jiangchao;

import com.sun.org.apache.xml.internal.utils.res.IntArrayWrapper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @class: internet
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/5/7
 */
public class internet {
    public static void main(String[] args) throws UnknownHostException {
        // 参数可以未IP地址，域名；然而通过IP建立的不会检查DNS服务器。
        // 需要getHostAddress()
        InetAddress a  = InetAddress.getByName("www.baidu.com");
        a.getHostAddress(); // 该方法才会检查DNS，返回IP地址
        byte[] bytes = a.getAddress();
        // v6是lentth = 16, v4的length = 4
        for (int i = 0; i < bytes.length; i++){
            byte b = bytes[i];
            int unsignedByte = b > 0 ? b : 256 + b;
            if (i > 0){
                System.out.print(":");
            }
            System.out.print(unsignedByte);
        }

        // 查找本地地址

        System.out.println();
    }
}
