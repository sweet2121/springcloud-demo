package com.wrd.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wrd.pojo.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class OrderService {

    /**
     * @HystrixCommand 地方方法本身提供了服务降级
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getOrderByIdFallbackMethod")
    public Order getById(Integer id){
        Order order = new Order();
        order.setId(id);
        order.setOrderNo("orderno"+id);
        order.setCreateTime(new Date());
        Random random = new Random();
        order.setUserId(random.nextInt());
        return order;
    }
    //getUserByIdFallbackMethod方法参数要和getOrderById参数一致。
    public Order getOrderByIdFallbackMethod(Integer id){
        return new Order();
    }
}
