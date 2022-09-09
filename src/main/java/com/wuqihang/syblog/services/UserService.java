package com.wuqihang.syblog.services;

import com.wuqihang.syblog.pojo.User;

import java.util.List;

/**
 * @author Wuqihang
 */
public interface UserService {
    boolean insert(User user);

    boolean update(User user);

    User getUser(String username);

    List<User> getAllUsers();

    boolean checkPassword(String username, String password);

    boolean exist(User user);

    boolean exist(String username);

    void delete(User user);

    User create(String username, String password);

    User create(User user);
}
