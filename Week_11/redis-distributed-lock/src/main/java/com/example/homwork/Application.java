package com.example.homwork;

import com.example.homwork.service.StockService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public  RedissonClient initRedisson(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("123").setDatabase(0);
        return  Redisson.create(config);
    }

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping(value = "/redissonLock")
    public String redissonLock() throws InterruptedException {
        RLock rLock = redissonClient.getLock("order");
        System.out.println("我进入了方法");

        rLock.lock(30, TimeUnit.SECONDS);
        System.out.println("获得了锁");
        Thread.sleep(3000);
        System.out.println("释放了锁");
        rLock.unlock();
        System.out.println("方法执行完成");

        return "方法执行完成";
    }

    @Autowired
    private StockService  stockService;

    @PutMapping(value = "/products/{productId}")
    public void reduce(@PathVariable("productId") Integer productId) {
        stockService.reduce(productId);
    }

    @PutMapping(value = "/products/{productId}/{stockQty}")
    public void init(@PathVariable("productId") Integer productId,@PathVariable("stockQty") Integer stockQty) {
        stockService.init(productId,stockQty);
    }
}
