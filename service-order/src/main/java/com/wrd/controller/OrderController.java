package com.wrd.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wrd.User;
import com.wrd.feign.UserFeignClient;
import com.wrd.feign.UserFeignClientFallback;
import com.wrd.pojo.Order;
import com.wrd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserFeignClient userFeignClient;
    /**
     *
     * 根据订单Id查询订单信息
     * @param id
     * @return
     */
    //当前方法如果出现服务调用问题，使用Hystrix逻辑来处理
    @RequestMapping("getById")
    @HystrixCommand(fallbackMethod = "getOrderByIdFallbackMethod")
    public Order getById(@RequestParam("id") Integer id){
        Order order = orderService.getById(id);
        //调用user服务接口
        //final String username = restTemplate.getForObject("http://localhost:8280/user/getUsernameById?id=111", String.class);
        //service-user是用户服务里面，spring.application.name的名字
       //final String username = restTemplate.getForObject("http://service-user/user/getUsernameById?id=111"+order.getUserId, String.class);

        //feign调用1
        String username = userFeignClient.getUsernameById(order.getUserId());
        //feign调用2
        //User user = userFeignClient.getById(order.getUserId());
        //order.setUsername(user.getUsername());
        //feign对象传参
        User u = new User();
        u.setId(111);
        User userById = userFeignClient.getUserById(u);
        order.setUsername(userById.getUsername());
        return order;
    }
    //getUserByIdFallbackMethod方法参数要和getOrderById参数一致。
    public Order getOrderByIdFallbackMethod(Integer id){
        return new Order();
    }
}
