package java.top.fuyuaaa.study_java.thread.tongxin.PIPE;

import java.io.IOException;
import java.io.PipedReader;

/**
 * @author: fuyuaaaaa
 * @description: 读取数据的线程
 * @program: study
 * @creat: 2018-09-13 19:09
 **/
public class ThreadRead extends Thread{
    private PipedReader input;

    public ThreadRead(PipedReader input){
        super();
        this.input = input;
    }

    @Override
    public void run(){

        try{
            System.out.println("read: ");
            char[] byteArray = new char[100];
            int readLength = input.read(byteArray);
            while (readLength!=-1){
                String newData = new String(byteArray,0,readLength);
                System.out.print(newData);
                readLength = input.read(byteArray);
            }
            System.out.println();
            input.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
