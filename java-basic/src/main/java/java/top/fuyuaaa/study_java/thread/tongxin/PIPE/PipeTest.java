package java.top.fuyuaaa.study_java.thread.tongxin.PIPE;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author: fuyuaaaaa
 * @description: 管道测试类
 * @program: study
 * @creat: 2018-09-13 19:09
 **/
public class PipeTest {
    public static void main(String[] args) {

        try {
            PipedReader pipedReader = new PipedReader();
            PipedWriter pipedWriter = new PipedWriter();

            pipedWriter.connect(pipedReader);
            //启动读线程
            ThreadRead threadRead = new ThreadRead(pipedReader);
            threadRead.start();

            Thread.sleep(2000);

            //启动写线程
            ThreadWrite threadWrite = new ThreadWrite(pipedWriter);
            threadWrite.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
