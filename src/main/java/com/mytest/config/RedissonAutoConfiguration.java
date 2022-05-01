package com.mytest.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass(Config.class)
@AutoConfigureBefore({RedisAutoConfiguration.class})
public class RedissonAutoConfiguration {


    @Bean("redissonClient")
    public RedissonClient initRedissonClient() {
        // 默认连接地址 127.0.0.1:6379
//        RedissonClient redisson = Redisson.create();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        config.setCodec(new StringCodec());
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
