package pers.springboot_redis.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.springboot_redis.seckill.dao.SeckillDao;
import pers.springboot_redis.seckill.dao.SuccessKilledDao;
import pers.springboot_redis.seckill.dao.cache.RedisDao;
import pers.springboot_redis.seckill.pojo.Exposer;
import pers.springboot_redis.seckill.pojo.Seckill;
import pers.springboot_redis.seckill.pojo.SeckillExecution;
import pers.springboot_redis.seckill.result.SeckillResult;
import pers.springboot_redis.seckill.service.SeckillService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ltx
 * @Date 9:31 2020/3/19
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    RedisDao redisDao;
    @Autowired
    private SeckillService seckillService;
    @Autowired
    SeckillDao dao;
    @Autowired
    SuccessKilledDao successKilledDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute( "list", seckillList);
        return "show";
    }

    @RequestMapping(value = "/{seckillid}/detail", method = RequestMethod.GET)
    public String showDetail(@PathVariable("seckillid")Long seckillid, Model model){
        Seckill seckill = seckillService.getById(seckillid);
        model.addAttribute("seckill", seckill);

        return "detail";
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillid}/exposer", method = RequestMethod.POST)
    public SeckillResult<Exposer> exposer(@PathVariable("seckillid") Long seckillid, Model model){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillid);
            result = new SeckillResult<>(true, exposer);
        }catch (Exception e){
            result = new SeckillResult<>(false, e.getMessage());
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillid}/{md5}/execution", method = RequestMethod.POST)
    public SeckillResult<SeckillExecution> excute(@PathVariable("seckillid") Long seckillid,  @PathVariable("md5") String md5){
        SeckillResult<SeckillExecution> executionResult;
        try {
            SeckillExecution execution = seckillService.excuteSeckill(seckillid, 2, md5);
            executionResult = new SeckillResult<>(true, execution);
        }catch (Exception e){
            executionResult = new SeckillResult<>(false, e.getMessage());
        }

        System.out.println("result:"+executionResult.toString());
        return executionResult;
    }


    @ResponseBody
    @RequestMapping("/test")
    public String tt(){
        Map<String, Object> params = new HashMap<>();
        params.put("seckillid", 3);
        params.put("phone", 54332);
        params.put("killtime", new Date());
        params.put("result", 0);

        dao.reduceNumber(params);

        System.out.println(params.get("result"));

        return "";
    }
}
