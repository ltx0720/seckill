package pers.springboot_redis.seckill.pojo;

import lombok.Data;

/**
 * @Author ltx
 * @Date 10:08 2020/3/19
 */
@Data
public class SeckillExecution {
    private long seckillid;
    private int state;
    private String stateInfo;
    //秒杀成功对象
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillid, int state, String stateInfo, SuccessKilled successKilled) {
        this.seckillid = seckillid;
        this.state = state;
        this.stateInfo = stateInfo;
        this.successKilled = successKilled;
    }
}
