package pers.springboot_redis.seckill.JavaConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pers.springboot_redis.seckill.dao.cache.RedisDao;
/**
 * @Author ltx
 * @Date 21:05 2020/3/21
 */
@Component
public class BeanConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisDao redisDao(){
        return new RedisDao(host, port);
    }
}
