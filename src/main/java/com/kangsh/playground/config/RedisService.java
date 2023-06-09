package com.kangsh.playground.config;

import com.kangsh.playground.util.JsonUtil;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate redisTemplate;

    public <T> T getCache(String key, Class<T> type) {
        Object value = redisTemplate.opsForValue().get(key);
        if (Objects.isNull(value)) {
            return null;
        }
        return JsonUtil.convertToObject(JsonUtil.convertToJsonStr(value), type);
    }

    public void addCache(String key, Object object) {
        redisTemplate.opsForValue().set(key, JsonUtil.convertToJsonStr(object));
    }
}
