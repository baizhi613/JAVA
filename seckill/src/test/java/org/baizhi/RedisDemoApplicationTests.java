package org.baizhi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedis() {
        // 写入数据
        redisTemplate.opsForValue().set("testKey", "testValue");

        // 读取数据
        String value = redisTemplate.opsForValue().get("testKey");
        System.out.println("Value from Redis: " + value);
    }
}
