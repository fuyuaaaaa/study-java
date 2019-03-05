package top.fuyuaaa.study.netty;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Channel Demo
 *
 * @author: fuyuaaa
 * @creat: 2019-03-05 09:41
 */
class NettyStudyDemoTest {

    private final static String FILE_PATH = "C:\\work\\workspaces\\IdeaProjects\\study-java\\java-basic\\src\\main\\resources\\file\\";
    private final static String TXT_PATH = FILE_PATH + "channelDemoFile.txt";


    /**
     * channel && buffer demo.
     * 注意 buf.flip() 的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据。
     * 使用Buffer读写数据一般遵循以下四个步骤：
     * 1. 写入数据到Buffer
     * 2. 调用flip()方法
     * 3. 从Buffer中读取数据
     * 4. 调用clear()方法或者compact()方法
     */
    @Test
    void channelAndBufferDemo() {
        try {
            RandomAccessFile file = new RandomAccessFile(TXT_PATH, "rw");
            FileChannel fileChannel = file.getChannel();
            //创建一个容量48的ByteBuffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            //往buffer中写数据
            int bytesRead = fileChannel.read(byteBuffer);
            while (-1 != bytesRead) {
                System.out.println("read: " + bytesRead + " bytes");
                //make buffer ready for read
                //flip()方法将buffer从 读模式 -> 写模式
                byteBuffer.flip();
                /* byteBuffer.position(6);//从第六位开始读*/
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());
                }
                //make buffer ready for writing
                byteBuffer.clear();
                bytesRead = fileChannel.read(byteBuffer);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * scattering reads demo
     * 分散
     */
    @Test
    void scatteringReads() {
        try {
            RandomAccessFile file = new RandomAccessFile(TXT_PATH, "rw");
            FileChannel fileChannel = file.getChannel();
            ByteBuffer[] buffers = getBufferArray();
            fileChannel.read(buffers);
            buffers[0].flip();
            while (buffers[0].hasRemaining()) {
                System.out.print((char) buffers[0].get());
            }
            buffers[1].flip();
            while (buffers[1].hasRemaining()) {
                System.out.print((char) buffers[1].get());
            }
            buffers[0].clear();
            buffers[1].clear();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ByteBuffer[] getBufferArray() {
        ByteBuffer b1 = ByteBuffer.allocate(6);
        ByteBuffer b2 = ByteBuffer.allocate(5);
        return new ByteBuffer[]{b1, b2};
    }

    /**
     * gathering writes demo
     * 聚集
     */
    @Test
    void gatheringWrites() {
        try {
            RandomAccessFile file = new RandomAccessFile(TXT_PATH, "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer[] buffers = getBufferArray();
            channel.write(buffers);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * transferFrom demo
     * position: 从position开始写入
     * transferTo 用法差不多
     */
    @Test
    void transferForm() {
        try {
            RandomAccessFile fromFile = new RandomAccessFile(TXT_PATH, "rw");
            FileChannel fromChannel = fromFile.getChannel();
            RandomAccessFile toFile = new RandomAccessFile(TXT_PATH, "rw");
            FileChannel toChannel = toFile.getChannel();
            toChannel.transferFrom(fromChannel, 11, fromChannel.size());
            ByteBuffer byteBuffer = ByteBuffer.allocate(22);
            toChannel.read(byteBuffer);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
            byteBuffer.clear();
            fromFile.close();
            toFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void selector() {
        //look webClient and webServer
    }
}
