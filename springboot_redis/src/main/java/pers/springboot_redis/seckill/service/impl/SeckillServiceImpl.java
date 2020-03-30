package pers.springboot_redis.seckill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import pers.springboot_redis.seckill.dao.SeckillDao;
import pers.springboot_redis.seckill.dao.SuccessKilledDao;
import pers.springboot_redis.seckill.dao.cache.RedisDao;
import pers.springboot_redis.seckill.exception.RepeatKillException;
import pers.springboot_redis.seckill.exception.SeckillCloseException;
import pers.springboot_redis.seckill.exception.SeckillException;
import pers.springboot_redis.seckill.pojo.Exposer;
import pers.springboot_redis.seckill.pojo.Seckill;
import pers.springboot_redis.seckill.pojo.SeckillExecution;
import pers.springboot_redis.seckill.pojo.SuccessKilled;
import pers.springboot_redis.seckill.service.SeckillService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author ltx
 * @Date 10:15 2020/3/19
 */
@Component
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Autowired
    private RedisDao redisDao;

    private String salt = "^$^432^$4ewsad6uh*WEY*(U3jh214u891";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long seckillid) {
        return seckillDao.queryById(seckillid);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillid) {
        // 首先尝试从Redis中获取
        Seckill seckill = redisDao.getSeckill(seckillid);

        if (seckill == null){
             seckill = seckillDao.queryById(seckillid);
             if (seckill == null) {
                 return new Exposer(false, seckillid);
             }
             redisDao.putSeckill(seckill);
        }

        Date start_time = seckill.getStart_time();
        Date end_time = seckill.getEnd_time();
        Date nowTime = new Date();

        if (nowTime.getTime() < start_time.getTime()
                || nowTime.getTime() > end_time.getTime()){

            return new Exposer(seckillid, false, nowTime, start_time, end_time);
        }
        String md5 = getMd5(seckillid);
        
        return new Exposer(true, md5, seckillid);
    }

    //执行秒杀逻辑：减库存 记录购买行为
    @Override
    @Transactional
    public SeckillExecution excuteSeckill(long seckillid, long userphone, String md5) {
        if (!md5.equals(getMd5(seckillid))){
            throw new SeckillException("data error");
        }
        Date nowTime = new Date();
        Map<String, Object> params = new HashMap<>();
        params.put("seckillid", seckillid);
        params.put("phone", userphone);
        params.put("killtime", nowTime);
        params.put("result", 0);

        try {
            seckillDao.reduceNumber(params);
        }catch (Exception e){
            int result = (int)params.get("result");

            if (result == -2){
                throw new SeckillException("秒杀出错");
            }else if (result == -1){
                throw new RepeatKillException("重复购买");
            }else if(result == 0){
                throw new SeckillCloseException("活动结束");
            }
        }

        SuccessKilled successKilled = successKilledDao.getByIdAndPhone(seckillid, userphone);
        return new SeckillExecution(seckillid, 1, "秒杀成功", successKilled);
    }

    private String getMd5(long seckillid){
        String base = seckillid + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());

        return md5;
    }

}
