package com.yuer.redis;

import com.yuer.redis.factory.RedissonTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.redisson.core.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RedisClientTest extends AbstractSpringTest {

    @Autowired
    RedissonTemplate redissonTemplate;

    @Test
    public void getLock() throws Exception {
        RLock lock = redissonTemplate.getLock("china");
        lock.lock();
        try {
            System.out.println("RLock holdCount:" + lock.getHoldCount() + "  name:" + lock.getName() + " lock:" + lock.isLocked());
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void getList() throws Exception {
        RPatternTopic<String> lock = redissonTemplate.getPatternTopic("");
        try {
            System.out.println("RPatternTopic.PatternNames:" + lock.getPatternNames());
        } finally {
        }
    }

    @Test
    public void getAtomic() throws Exception {
        final RAtomicLong atomicLong = redissonTemplate.getAtomicLong("anyAtomicLong");
        atomicLong.set(500);
        for (int i = 0; i < 1000; i++) {
            new Runnable() {
                @Override
                public void run() {
                    if (atomicLong.get() == 0) {
                        System.err.println("GetAtomic: error less than 0");
                    } else {
                        long r = atomicLong.decrementAndGet();
                        System.out.println("GetAtomic: " + r);
                    }
                }
            }.run();
        }
    }

    public boolean deductCashAccount(String actid, int amount) throws FileNotFoundException, IOException, InterruptedException {
        /** 扣余额直接操作redis缓存数据库，key由账户ID，-字符，字符串balance组成 */
        long start = System.currentTimeMillis();

        String key = "CASH_" + actid;
        String lock_point = "LOCK_CASH_" + actid;
        RLock lock = redissonTemplate.getLock(lock_point);// 获取账户锁对象
        logger.info("get lock " + lock_point);
        boolean locked = lock.tryLock(10, 60, TimeUnit.SECONDS);// 尝试锁住账户对象,waitTime第一个参数获取锁超时时间30毫秒,leaseTime第二参数,锁自动释放时间
        if (!locked) {
            logger.info("cann't get lock ,id=" + actid);
            return false;
        }
        // lock.lock();
        logger.info("get lock " + lock_point + " ok");
        RBucket<Integer> atomicbalance = redissonTemplate.getBucket(key);// 获取原子余量
        boolean result_flag = true;
        if (atomicbalance.get() == 0) {
            logger.error(" error ,balance less than or equal to 0");
            result_flag = false;
        } else {
            atomicbalance.set(atomicbalance.get().intValue() - amount);// 扣除余量
            logger.info("balance is " + atomicbalance.get());
            result_flag = true;
        }
        lock.unlock();// 解锁
        logger.info("debut cash , cost time:" + (System.currentTimeMillis() - start));
        return result_flag;
    }

    @Test
    public void atomicDouble() throws InterruptedException {
        RCountDownLatch latch = redissonTemplate.getCountDownLatch("anyCountDownLatch");
        int size = 10;
        latch.trySetCount(size);
        System.out.println("RCountDownLatch trySetCount");

        for (int i = 0; i < size; i++) {
            final int finalI = i;
            Thread thread = new Thread(new Runnable() {// in other thread or other JVM
                @Override
                public void run() {
                    RCountDownLatch latch = redissonTemplate.getCountDownLatch("anyCountDownLatch");
                    latch.countDown();
                    System.out.println("RCountDownLatch (" + String.valueOf(finalI) + ") countDown");
                }
            });
            thread.setName(String.valueOf(i));
            thread.start();
        }
        latch.await();
        System.out.println("RCountDownLatch over");
    }

    @Test
    public void topicTest() throws InterruptedException {
        RTopic<String> topic = redissonTemplate.getTopic("anyTopic");
        topic.addListener(new MessageListener<String>() {
            public void onMessage(String channel, String message) {
                System.out.print("" + message);
            }
        });

        // in other thread or other JVM
        // RTopic<String> topic = redissonTemplate.getTopic("anyTopic");
    }

    @Test
    public void pushTopicTest() throws InterruptedException {
        // in other thread or other JVM
        RTopic<String> topic = redissonTemplate.getTopic("anyTopic");
        long clientsReceivedMessage = topic.publish("ssssssssssssss");
    }


    @Test
    public void topicPatternTest() {
        RPatternTopic<String> topic1 = redissonTemplate.getPatternTopic("topic1.*");
        int listenerId = topic1.addListener(new PatternMessageListener<String>() {
            @Override
            public void onMessage(String pattern, String channel, String msg) {
                Assert.fail();
            }
        });
    }

    @Test
    public void bloomFilterTest() {
        RBloomFilter<String> bloomFilter = redissonTemplate.getBloomFilter("sample");
        // initialize bloom filter with
        // expectedInsertions = 55000000
        // falseProbability = 0.03
        bloomFilter.tryInit(55000000L, 0.03);
//        bloomFilter.add(new SomeObject("field1Value", "field2Value"));
//        bloomFilter.add(new SomeObject("field5Value", "field8Value"));
//        bloomFilter.contains(new SomeObject("field1Value", "field8Value"));
    }

    @Test
    public void getHyperLogLogTest() {
        RHyperLogLog<Integer> log = redissonTemplate.getHyperLogLog("log");
        log.add(1);
        log.add(2);
        log.add(3);
        System.out.println("RHyperLogLog.count:" + log.count());
    }

    @Test
    public void getKeys() {
        RKeys keys = redissonTemplate.getKeys();
        System.out.println("RHyperLogLog.count:" + keys.count());
        for (String key : keys.getKeys()) {
            System.out.println("RHyperLogLog.key:" + key);
        }
    }

    @Test
    public void addSet() {
        RSet<Object> sets = redissonTemplate.getSet("set");
        sets.add("x");
        sets.add("y");
        sets.add("z");

        RSet<Object> another = redissonTemplate.getSet("set");
        System.out.println(another.size());

    }

    @Test
    public void addList() {
        RList<Object> list = redissonTemplate.getList("list");
        list.add("x");
        list.add("y");
        list.add("z");

        RList<Object> another = redissonTemplate.getList("list");
        System.out.println(another.size());
    }

    @Test
    public void queue() {
        RBlockingQueue<Object> queue = redissonTemplate.getBlockingQueue("queue");
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        queue.add("e");

        int i = 0;
        while (++i < 3) {
            RBlockingQueue<Object> another = redissonTemplate.getBlockingDeque("queue");
            Object one = another.poll();
            System.out.println("size:" + another.size() + ", one:" + one);
        }

        System.out.println("Queue size before clear:" + queue.size());
        queue.clear();
        System.out.println("Queue size after  clear:" + queue.size());
    }

}
