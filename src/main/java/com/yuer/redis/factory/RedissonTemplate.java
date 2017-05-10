package com.yuer.redis.factory;

import org.redisson.Config;
import org.redisson.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.core.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedissonTemplate implements RedissonClient {

    private RedissonClient redisson;

    public void setRedisson(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public <V> RBucket<V> getBucket(String name) {
        return redisson.getBucket(name);
    }

    @Override
    public <V> RBucket<V> getBucket(String name, Codec codec) {
        return redisson.getBucket(name, codec);
    }

    @Override
    public RBuckets getBuckets() {
        return redisson.getBuckets();
    }

    @Override
    public RBuckets getBuckets(Codec codec) {
        return redisson.getBuckets(codec);
    }

    @Override
    public <V> RHyperLogLog<V> getHyperLogLog(String name) {
        return redisson.getHyperLogLog(name);
    }

    @Override
    public <V> RHyperLogLog<V> getHyperLogLog(String name, Codec codec) {
        return redisson.getHyperLogLog(name, codec);
    }

    @Override
    public <V> RList<V> getList(String name) {
        return redisson.getList(name);
    }

    @Override
    public <V> RList<V> getList(String name, Codec codec) {
        return redisson.getList(name, codec);
    }

    @Override
    public <K, V> RMap<K, V> getMap(String name) {
        return redisson.getMap(name);
    }

    @Override
    public <K, V> RMap<K, V> getMap(String name, Codec codec) {
        return redisson.getMap(name, codec);
    }

    @Override
    public RLock getLock(String name) {
        return redisson.getLock(name);
    }

    @Override
    public <V> RSet<V> getSet(String name) {
        return redisson.getSet(name);
    }

    @Override
    public <V> RSet<V> getSet(String name, Codec codec) {
        return redisson.getSet(name, codec);
    }

    @Override
    public <V> RSortedSet<V> getSortedSet(String name) {
        return redisson.getSortedSet(name);
    }

    @Override
    public <V> RSortedSet<V> getSortedSet(String name, Codec codec) {
        return redisson.getSortedSet(name, codec);
    }

    @Override
    public <V> RScoredSortedSet<V> getScoredSortedSet(String name) {
        return redisson.getScoredSortedSet(name);
    }

    @Override
    public <V> RScoredSortedSet<V> getScoredSortedSet(String name, Codec codec) {
        return redisson.getScoredSortedSet(name, codec);
    }

    @Override
    public RLexSortedSet getLexSortedSet(String name) {
        return redisson.getLexSortedSet(name);
    }

    @Override
    public <M> RTopic<M> getTopic(String name) {
        return redisson.getTopic(name);
    }

    @Override
    public <M> RTopic<M> getTopic(String name, Codec codec) {
        return redisson.getTopic(name);
    }

    @Override
    public <M> RPatternTopic<M> getPatternTopic(String pattern) {
        return redisson.getPatternTopic(pattern);
    }

    @Override
    public <M> RPatternTopic<M> getPatternTopic(String pattern, Codec codec) {
        return redisson.getPatternTopic(pattern, codec);
    }

    @Override
    public <V> RQueue<V> getQueue(String name) {
        return redisson.getQueue(name);
    }

    @Override
    public <V> RQueue<V> getQueue(String name, Codec codec) {
        return redisson.getQueue(name, codec);
    }

    @Override
    public <V> RBlockingQueue<V> getBlockingQueue(String name) {
        return redisson.getBlockingQueue(name);
    }

    @Override
    public <V> RBlockingQueue<V> getBlockingQueue(String name, Codec codec) {
        return redisson.getBlockingQueue(name, codec);
    }

    @Override
    public <V> RDeque<V> getDeque(String name) {
        return redisson.getDeque(name);
    }

    @Override
    public <V> RDeque<V> getDeque(String name, Codec codec) {
        return redisson.getDeque(name, codec);
    }

    @Override
    public RAtomicLong getAtomicLong(String name) {
        return redisson.getAtomicLong(name);
    }

    @Override
    public RCountDownLatch getCountDownLatch(String name) {
        return redisson.getCountDownLatch(name);
    }

    @Override
    public RScript getScript() {
        return redisson.getScript();
    }

    @Override
    public RRemoteService getRemoteSerivce() {
        return redisson.getRemoteSerivce();
    }

    @Override
    public RBatch createBatch() {
        return redisson.createBatch();
    }

    @Override
    public RKeys getKeys() {
        return redisson.getKeys();
    }

    @Override
    public void shutdown() {
        redisson.shutdown();
    }

    @Override
    public void shutdown(long quietPeriod, long timeout, TimeUnit unit) {

    }

    @Override
    public Config getConfig() {
        return redisson.getConfig();
    }

    @Override
    public NodesGroup<Node> getNodesGroup() {
        return redisson.getNodesGroup();
    }

    @Override
    public NodesGroup<ClusterNode> getClusterNodesGroup() {
        return redisson.getClusterNodesGroup();
    }


    @Override
    public <V> RGeo<V> getGeo(String name) {
        return redisson.getGeo(name);
    }

    @Override
    public <V> RGeo<V> getGeo(String name, Codec codec) {
        return redisson.getGeo(name, codec);
    }

    @Override
    public <V> RSetCache<V> getSetCache(String name) {
        return redisson.getSetCache(name);
    }

    @Override
    public <V> RSetCache<V> getSetCache(String name, Codec codec) {
        return redisson.getSetCache(name, codec);
    }

    @Override
    public <K, V> RMapCache<K, V> getMapCache(String name, Codec codec) {
        return redisson.getMapCache(name, codec);
    }

    @Override
    public <K, V> RMapCache<K, V> getMapCache(String name) {
        return redisson.getMapCache(name);
    }

    @Override
    public <V> List<RBucket<V>> findBuckets(String pattern) {
        return redisson.findBuckets(pattern);
    }

    @Override
    public <V> Map<String, V> loadBucketValues(Collection<String> keys) {
        return redisson.loadBucketValues(keys);
    }

    @Override
    public <V> Map<String, V> loadBucketValues(String... keys) {
        return redisson.loadBucketValues(keys);
    }

    @Override
    public void saveBuckets(Map<String, ?> buckets) {
        redisson.saveBuckets(buckets);
    }

    @Override
    public <K, V> RListMultimap<K, V> getListMultimap(String name) {
        return redisson.getListMultimap(name);
    }

    @Override
    public <K, V> RListMultimap<K, V> getListMultimap(String name, Codec codec) {
        return redisson.getListMultimap(name, codec);
    }

    @Override
    public <K, V> RListMultimapCache<K, V> getListMultimapCache(String name) {
        return redisson.getListMultimapCache(name);
    }

    @Override
    public <K, V> RListMultimapCache<K, V> getListMultimapCache(String name, Codec codec) {
        return redisson.getListMultimapCache(name, codec);
    }

    @Override
    public <K, V> RSetMultimap<K, V> getSetMultimap(String name) {
        return redisson.getSetMultimap(name);
    }

    @Override
    public <K, V> RSetMultimap<K, V> getSetMultimap(String name, Codec codec) {
        return redisson.getSetMultimap(name, codec);
    }

    @Override
    public <K, V> RSetMultimapCache<K, V> getSetMultimapCache(String name) {
        return redisson.getSetMultimapCache(name);
    }

    @Override
    public <K, V> RSetMultimapCache<K, V> getSetMultimapCache(String name, Codec codec) {
        return redisson.getSetMultimapCache(name, codec);
    }

    @Override
    public RSemaphore getSemaphore(String name) {
        return redisson.getSemaphore(name);
    }

    @Override
    public RReadWriteLock getReadWriteLock(String name) {
        return redisson.getReadWriteLock(name);
    }

    @Override
    public <V> RBlockingDeque<V> getBlockingDeque(String name) {
        return redisson.getBlockingDeque(name);
    }

    @Override
    public <V> RBlockingDeque<V> getBlockingDeque(String name, Codec codec) {
        return redisson.getBlockingDeque(name, codec);
    }

    @Override
    public RAtomicDouble getAtomicDouble(String name) {
        return redisson.getAtomicDouble(name);
    }

    @Override
    public RBitSet getBitSet(String name) {
        return redisson.getBitSet(name);
    }

    @Override
    public <V> RBloomFilter<V> getBloomFilter(String name) {
        return redisson.getBloomFilter(name);
    }

    @Override
    public <V> RBloomFilter<V> getBloomFilter(String name, Codec codec) {
        return redisson.getBloomFilter(name, codec);
    }

    @Override
    public boolean isShutdown() {
        return redisson.isShutdown();
    }

    @Override
    public boolean isShuttingDown() {
        return redisson.isShuttingDown();
    }

}