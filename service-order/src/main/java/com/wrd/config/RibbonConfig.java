package com.wrd.config;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {
    // LoadBalanced 添加这个注解，在调用的时候就有负载均衡的功能
    //默认的负载均衡算法是轮询
   @Bean
   @LoadBalanced
   public RestTemplate restTemplate(){
       return new RestTemplate();
   }

    //配置负载均衡算法
    //默认的负载均衡算法是轮询
    @Bean
    public IRule iRule(){
        /** 随机选取一个服务，进行访问 **/
        return new RandomRule();
        /** 轮询方式选取一个服务，进行访问 **/
        //return new RoundRobinRule();

        /** 如果一个服务挂了，会尝试几次访问，如果几次访问失败后，下次就跳过失败的服务。 **/
        //return new RetryRule();
        /** 当高并发时，会选中最空闲当服务 **/
        //return new BestAvailableRule();
        /** 按照权重访问 **/
        //return new WeightedResponseTimeRule();
        /** 先过滤清单再轮训 **/
//        return new PredicateBasedRule() {
//            @Override
//            public AbstractServerPredicate getPredicate() {
//                return null;
//            }
//        };
    }
}
