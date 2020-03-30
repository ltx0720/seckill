package pers.springboot_redis.seckill.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author ltx
 * @Date 10:04 2020/3/19
 */
@Data
public class Exposer {
    private long seckillid;
    private boolean expose;
    private String md5;
    private Date now;
    private Date start;
    private Date end;

    public Exposer(boolean expose, long seckillid) {
        this.expose = expose;
        this.seckillid = seckillid;
    }

    public Exposer(boolean expose, String md5, long seckillid) {
        this.expose = expose;
        this.md5 = md5;
        this.seckillid = seckillid;
    }


    public Exposer(long seckillid, boolean expose, Date now, Date start, Date end) {
        this.seckillid = seckillid;
        this.expose = expose;
        this.now = now;
        this.start = start;
        this.end = end;
    }
}
