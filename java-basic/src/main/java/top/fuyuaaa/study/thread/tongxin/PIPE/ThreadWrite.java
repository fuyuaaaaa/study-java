package top.fuyuaaa.study.thread.tongxin.PIPE;

import java.io.IOException;
import java.io.PipedWriter;

/**
 * @author: fuyuaaaaa
 * @description: 发送数据的线程
 * @program: study
 * @creat: 2018-09-13 19:06
 **/
public class ThreadWrite extends Thread {

    private PipedWriter out;

    public ThreadWrite(PipedWriter out) {
        super();
        this.out = out;
    }

    @Override
    public void run() {
        try {
            System.out.println("write: ");
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                out.write(outData);
                System.out.print(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
