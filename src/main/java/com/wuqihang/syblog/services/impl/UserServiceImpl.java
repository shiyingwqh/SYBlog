package com.wuqihang.syblog.services.impl;

import com.wuqihang.syblog.mapper.UserMapper;
import com.wuqihang.syblog.pojo.User;
import com.wuqihang.syblog.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author Wuqihang
 */
@Component
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean insert(User user) {
        try {
            mapper.insert(user);
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        try {
            mapper.update(user);
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
        return true;
    }

    @Override
    public User getUser(String username) {
        return mapper.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return mapper.getAllUsers();
    }

    @Override
    public boolean checkPassword(String username, String password) {
        User user = mapper.getUserByUsername(username);
        return user != null && Objects.equals(user.getPassword(), password);
    }

    @Override
    public boolean exist(User user) {
        return exist(user.getUsername());
    }

    @Override
    public boolean exist(String username) {
        return mapper.getUserByUsername(username) != null;
    }

    @Override
    public void delete(User user) {
        mapper.delete(user.getId());
    }

    @Override
    public User create(String username, String password) {
        if (exist(username)) return null;
        return new User(String.valueOf(System.currentTimeMillis()), username, password);
    }

    @Override
    public User create(User user) {
        return create(user.getUsername(), user.getPassword());
    }
}
