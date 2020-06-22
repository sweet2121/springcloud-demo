package com.wrd.feign;

import com.wrd.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements  UserFeignClient{
    @Override
    public String getUsernameById(Integer id) {
        return "查询失败。。。"+id;
    }

    @Override
    public User getById(Integer id) {
        User user = new User();
        user.setUsername("查询失败。。。。"+id);
        return user;
    }

    @Override
    public User getUserById(User user) {
        User user1 = new User();
        user1.setUsername("查询失败。。。。"+user.getId());
        return user1;
    }
}
