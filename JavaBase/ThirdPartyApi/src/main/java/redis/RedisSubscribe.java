package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisSubscribe {
    public static void main(String[] args) {
        RedisSubscriber subscriber = new RedisSubscriber();
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.6.25", 6379, 1000);
        Jedis jedis = jedisPool.getResource();
        jedis.subscribe(subscriber, "topic");
    }
}
