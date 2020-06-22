package com.wrd.controller;

import com.wrd.User;
import com.wrd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private Integer serverPort;

    /**
     * 根据用户Id，查询用户名称
     * @param id
     * @return
     */
    @GetMapping("getUsernameById")
    public String getUsernameById(@RequestParam("id") Integer id){

        System.out.println("===="+serverPort);
        return userService.getById(id).getUsername();
    }

    /**
     * 根据用户id，查询用户信息
     * @param id
     * @return
     */
    @GetMapping("getById")
    public User getById(@RequestParam("id") Integer id){
        return userService.getById(id);
    }

    /**
     * 根据用户id，查询用户信息
     * @param user
     * @return
     */
    @PostMapping("getUserById")
    public User getUserById(@RequestBody User user){
        return userService.getById(user.getId());
    };

}
