package pers.springboot_redis.seckill.result;

import lombok.Data;

/**
 * @Author ltx
 * @Date 23:28 2020/3/19
 *
 * 封装后端返回的信息，Json到前端
 */
@Data
public class SeckillResult<T> {
   private boolean success;
   private T data;
   private String error;

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
}
