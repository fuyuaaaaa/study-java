package top.fuyuaaa.study.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author: fuyuaaaaa
 * @description: InetAddress
 * @program: study
 * @creat: 2018-09-08 16:36
 **/
public class InetAddressTest {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();//获取本机InetAddress实例
        System.out.println("name" + address.getHostName());//computer name
        System.out.println("ip" + address.getHostAddress());
        byte[] bytes = address.getAddress(); //自己数组形式的ip
        System.out.println("" + Arrays.toString(bytes));
        System.out.println(address);

        //根据机器名虎丘InetAddress实例
        InetAddress address1 = InetAddress.getByName("fuyuaaa-computer");
        System.out.println("name" + address1.getHostName());
        System.out.println("ip" + address1.getHostAddress());

        System.out.println();

    }
}
