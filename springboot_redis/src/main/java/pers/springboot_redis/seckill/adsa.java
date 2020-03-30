package pers.springboot_redis.seckill;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author ltx
 * @Date 18:57 2020/3/29
 */
@Controller
public class adsa {

    @RequestMapping("/test")
    public String test1(){
        return "test";
    }
}
