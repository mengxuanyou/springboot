package com.wbkit.bigscreen.services.impl;

import com.wbkit.bigscreen.entity.User;
import com.wbkit.bigscreen.mapper.UserMapper;
import com.wbkit.bigscreen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public int insert(Integer id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return userMapper.insert(user);
    }
}
