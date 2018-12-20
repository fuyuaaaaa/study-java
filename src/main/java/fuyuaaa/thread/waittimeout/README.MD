#### 等待通知经典范式

1. 获取对象锁
2. 如果条件不满足，那么调用对象的wait()方法，被通知后仍要检查条件
3. 条件满足则执行对应的逻辑

伪代码
```
synchronized(对象){
    while(条件不满足) {
         对象.wait();
    }
    对应的处理逻辑
}
```

#### 等待超时模式

- 对等待/通知加上超时判断
    - 假设超时时间段是T，则now+T后超时
    - 定义：
        - remaining=T
        - future=now+T
        
伪代码
```java
//对当前对象加锁
public class test{
    public synchronized Object get(Long mills) {
        long future = System.currentTimeMillis()+mills;
        long remaining = mills;
        //当超时大于0并且返回值不满足要求
        while ((result == null) && remaining > 0) {
            wait(remaining);
            remaining = future - System.currentTimeMillis();
        }
        return result;
    }
}
```
        

