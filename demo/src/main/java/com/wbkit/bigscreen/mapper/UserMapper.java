package com.wbkit.bigscreen.mapper;

import com.wbkit.bigscreen.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> getAllUser();

    int insert(User user);
}
