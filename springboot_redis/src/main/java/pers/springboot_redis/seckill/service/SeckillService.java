package pers.springboot_redis.seckill.service;

import org.springframework.stereotype.Service;
import pers.springboot_redis.seckill.pojo.Exposer;
import pers.springboot_redis.seckill.pojo.Seckill;
import pers.springboot_redis.seckill.pojo.SeckillExecution;

import java.util.List;

/**
 * @Author ltx
 * @Date 9:14 2020/3/19
 */
@Service
public interface SeckillService  {

    //查询所有秒杀记录
    List<Seckill> getSeckillList();

    Seckill getById(long seckillid);

    //暴露秒杀地址接口
    //时间未到则输出倒计时
    Exposer exportSeckillUrl(long seckillid);

    SeckillExecution excuteSeckill(long seckillid, long userphone, String md5);
}
