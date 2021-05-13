package com.example.homwork.service.impl;

import com.example.homwork.service.StockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void reduce(Integer productId) {
        String lockKey = productId + "_lock";
        String redisKey = productId + "_stock";

        RLock rLock = redissonClient.getLock(lockKey);
        rLock.lock(10, TimeUnit.SECONDS);

        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(redisKey));
        if (stock - 1 < 0) {
            System.out.println("失败了，库存不足！");
            return;
        }

       Long res= stringRedisTemplate.opsForValue().decrement(redisKey);//库存数-1
        System.out.println("剩余库存：" + res);

        rLock.unlock();
    }

    @Override
    public void init(Integer productId, Integer stockQty) {
        String lockKey = productId + "_lock";
        String redisKey = productId + "_stock";

        RLock rLock = redissonClient.getLock(lockKey);
        rLock.lock(10, TimeUnit.SECONDS);

        stringRedisTemplate.opsForValue().set(redisKey, String.valueOf(stockQty));
        System.out.println("初始化库存：" + stockQty);

        rLock.unlock();
    }
}
