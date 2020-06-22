package com.wrd.service;



import com.wrd.User;
import org.springframework.stereotype.Service;

import javax.sql.RowSet;

@Service
public class UserService {

    /**
     * 通过用户id,查询用户
     * @param id
     * @return
     */
    public User getById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername("username"+id);
        return user;
    }

}
