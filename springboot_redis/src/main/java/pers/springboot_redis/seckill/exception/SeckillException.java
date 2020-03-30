package pers.springboot_redis.seckill.exception;

/**
 * @Author ltx
 * @Date 10:12 2020/3/19
 *
 * 秒杀业务相关异常
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
