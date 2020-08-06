package top.fuyuaaa.study.basic.nio;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : fuyuaaa
 * @date : 2020-07-16 10:15
 */
public class NioTest {

    @Test
    public void testFileChannel() throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("/Users/fuyuaaa/work/IdeaProjects/study-java/java-basic/src/main/java/top/fuyuaaa/study/basic/nio/test.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    @Test
    public void testWriteFile() throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/fuyuaaa/Desktop/test.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put("hello,fuyu!".getBytes());

        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}
