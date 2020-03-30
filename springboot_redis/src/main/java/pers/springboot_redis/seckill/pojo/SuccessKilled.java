package pers.springboot_redis.seckill.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author ltx
 * @Date 23:21 2020/3/17
 */
@Data
public class SuccessKilled {
    private long seckillid;
    private long userphone;
    private short state;
    private Date createtime;
    private Seckill seckill;
}
