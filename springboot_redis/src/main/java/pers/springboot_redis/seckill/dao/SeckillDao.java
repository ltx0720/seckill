package pers.springboot_redis.seckill.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import pers.springboot_redis.seckill.pojo.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author ltx
 * @Date 23:25 2020/3/17
 */
@Mapper
public interface SeckillDao {

    @Select("call execute_seckill(" +
            "#{seckillid, mode=IN, jdbcType=BIGINT}," +
            "#{phone, mode=IN, jdbcType=BIGINT}," +
            "#{killtime, mode=IN, jdbcType=TIMESTAMP}, " +
            "#{result, mode=OUT, jdbcType=INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void reduceNumber(Map<String, Object> params);

    @Select("select seckill_id, seckill_id, name, number, start_time, end_time, create_time " +
            "from seckill " +
            "where seckill_id = #{seckillid}")
    Seckill queryById(long seckillid);

    @Select("select seckill_id, seckill_id, name, number, start_time, end_time, create_time " +
            "from seckill " +
            "limit #{offset}, #{rows}")
    List<Seckill> queryAll(@Param("offset") int offset, @Param("rows") int rows);
}
