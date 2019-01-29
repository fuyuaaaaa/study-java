package top.fuyuaaa.study.thread.waittimeout;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author: fuyuaaaaa
 * @creat: 2018-12-18 22:50
 */
public class ConnectionDriver {

    static class ConnectionHandle implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                Thread.sleep(100);
            }
            return null;
        }
    }

    public static final Connection creatConnection(){
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[]{Connection.class}, new ConnectionHandle());
    }
}
