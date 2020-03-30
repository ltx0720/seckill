package pers.springboot_redis.seckill.exception;

/**
 * @Author ltx
 * @Date 10:11 2020/3/19
 *
 * 重复秒杀异常
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
