package pers.springboot_redis.seckill.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author ltx
 * @Date 23:21 2020/3/17
 */
@Data
public class Seckill {
    private long seckill_id;
    private String name;
    private int number;
    private Date start_time;
    private Date end_time;
    private Date create_time;
}
