### 待实现
  
  - 可重入锁
  

### 分布式锁

分布式锁，是指在分布式的部署环境下，通过锁机制让多客户端互斥地对共享进程进行访问。

#### 要求

- 在分布式系统环境下，一个方法在同一时间只能被一个机器的一个线程执行；
- 高可用的获取锁与释放锁；
- 高性能的获取锁与释放锁；
- 具备可重入特性；
- 具备锁失效机制，防止死锁；
- 具备阻塞锁特性，即没有获取到锁将继续等待获取锁；
- 具备非阻塞锁特性，即没有获取到锁将直接返回获取锁失败。

### 基于redis的分布式锁

#### 加锁

通过redis高版本的set命令

- 通过jedis的set方法
    - jedis.set(lockName, lockValue, "NX", "PX", expireTime);
    - 参数：通过这些参数的可以实现原子性
        - lockName: 锁的名称，redis的key
        - lockValue: 锁的值，redis的value
            - 在释放锁时先判断是否为同一个锁
        - "NX"，如果不存在就设置这个key
        - "PX"，过期时间单位为毫秒，"EX"，单位为秒
        - expireTime，过期时间
- 低版本通过setnx，expire，getSet三个命令

#### 解锁

通过redis的del命令

在释放锁之前先判断

```java
        if (lockValue.equals(jedis.get(lockKey))) {
            jedis.del(lockKey);
        }
```
代码分析：先获取再判断后删除的操作不属于原子操作，并发时存在问题。
解决方法：通过Lua脚本合并这三个操作
```java
    if redis.call("get",KEYS[1]) == ARGV[1] then
        return redis.call("del",KEYS[1])
    else
        return 0
    end
```
通过`jedis.eval()`执行脚本
```java
jedis.eval(script, 1, lockKey, lockValue);
```

#### Redisson的使用

注入 redisson client
```java
    <redisson:client id="redissonClient">
        <redisson:single-server address="${redis.node.address}"
                                password="${redis.password}"
                                connection-pool-size="60"/>
    </redisson:client>
```

配置文件
```java
redis.node.address=redis://106.14.169.161:6379
redis.password=Fuyu742423672

```

使用
```java
    @Autowired
    RedissonClient redissonClient;

    public void testRedisson() {
        //get lock
        RLock rLock = redissonClient.getLock(UUID);
        try {
            //lock
            rLock.tryLock(10, 10, TimeUnit.MINUTES);
            log.info("lock successfully！");
            //业务逻辑
            ...
        } catch (InterruptedException e) {
            log.error("error!");
        } finally {
            rLock.unlock();
            log.info("unlock key : {}",UUID);
        }
    }
```

#### Redisson源码
待续...

[fuyuaaa](https://github.com/fuyuaaa/study-java/tree/master/src/main/java/fuyuaaa/thread/redislock)
