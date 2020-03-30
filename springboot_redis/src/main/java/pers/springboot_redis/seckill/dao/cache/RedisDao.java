package pers.springboot_redis.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.springboot_redis.seckill.pojo.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.xml.bind.annotation.XmlElementRef;

/**
 * @Author ltx
 * @Date 23:11 2020/3/17
 */
//@Component
public class RedisDao {

    private JedisPool jedisPool;
    private final RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip, int port) {
        this.jedisPool = new JedisPool(ip, port);
    }

    public Seckill getSeckill(long seckillid){
        Jedis jedis = jedisPool.getResource();
        try {
            String key = "seckill"+seckillid;
            byte[] bytes = jedis.get(key.getBytes());

            if (bytes != null){
                Seckill seckill = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);

                return seckill;
            }
        }catch (Exception e){

        }finally {
            jedis.close();
        }
        return null;
    }

    public String putSeckill(Seckill seckill){
        byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

        Jedis jedis = jedisPool.getResource();
        String key = "seckill:" + seckill.getSeckill_id();
        String result = jedis.set(key.getBytes(), bytes);

        return result;
    }
}
