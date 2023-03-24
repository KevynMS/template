package com.template.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisClient {

    private final JedisPool jedisPool;

    public <T> T getValue(String key, Class<T> typeResult) {
        try (Jedis jedis = jedisPool.getResource()) {
            Gson gson = new Gson();
            return gson.fromJson(jedis.get(key), typeResult);
        }
    }

}
