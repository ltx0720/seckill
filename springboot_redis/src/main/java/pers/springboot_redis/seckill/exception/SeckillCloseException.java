package pers.springboot_redis.seckill.exception;

import lombok.Data;

/**
 * @Author ltx
 * @Date 10:11 2020/3/19
 *
 * 秒杀关闭异常
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
