package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RedisPublish {
    public static void main(String[] args) throws InterruptedException {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),"192.168.6.25",6379, 1000);
        Jedis resource = jedisPool.getResource();
        List<String> stringList=new ArrayList<>();
        stringList.add("hello world");
        for (String s : stringList) {
            resource.publish("topic",s);
        }


    }
}
