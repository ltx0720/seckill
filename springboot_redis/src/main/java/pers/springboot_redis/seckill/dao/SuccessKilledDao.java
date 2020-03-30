package pers.springboot_redis.seckill.dao;

import org.apache.ibatis.annotations.*;
import pers.springboot_redis.seckill.pojo.SuccessKilled;

import java.util.Date;

/**
 * @Author ltx
 * @Date 23:25 2020/3/17
 */
@Mapper
public interface SuccessKilledDao {

    @Select("select seckill_id, user_phone, state, create_time " +
            "from success_killed " +
            "where seckill_id=#{seckillid} and user_phone=#{userphone}")
    @Results(value = {
            @Result(column = "seckill_id", property = "seckillid"),
            @Result(column = "user_phone", property = "userphone"),
            @Result(column = "create_time", property = "createtime"),
            @Result(column = "state", property = "state"),
            @Result(column = "seckill_id", property = "seckill", one = @One(select = "pers.springboot_redis.seckill.dao.SeckillDao.queryById"))
    })
    SuccessKilled getByIdAndPhone(@Param("seckillid")long seckillid,  @Param("userphone") long userphone);


    @Insert("insert into success_killed " +
            "(seckill_id, user_phone, state, create_time) " +
            "values(#{seckillid}, #{phone}, #{state}, #{createtime})")
    int insertSuccessKilled(@Param("seckillid")long seckillid, @Param("phone")long phone,
                            @Param("state")int state, @Param("createtime")Date creattime);

}
