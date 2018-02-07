package com.wbkit.bigscreen.services;

import com.wbkit.bigscreen.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    int insert(Integer id, String name);
}
