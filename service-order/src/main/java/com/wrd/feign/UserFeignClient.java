package com.wrd.feign;

import com.wrd.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "service-user",fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    /**
     * 根据用户Id，查询用户名称
     * @RequestParam注解必须加，而且必须要指定value
     * @param id
     * @return
     */
    @GetMapping("/user/getUsernameById")
    public String getUsernameById(@RequestParam("id") Integer id);


    /**
     * 根据用户id，查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/user/getById")
    public User getById(@RequestParam("id") Integer id);

    /**
     * 根据用户id，查询用户信息
     * @param user
     * @return
     */
    @PostMapping ("/user/getUserById")
    public User getUserById(@RequestBody User user);

}
