package com.wuqihang.syblog.security;

import com.wuqihang.syblog.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wuqihang
 */

public interface TokenManager {
     void putToken(Token token);
     Token createToken(User user);
     void deleteToken(Token token);
     void deleteToken(String token);
     boolean checkToken(String token);
     boolean checkToken(String token, HttpServletRequest request);

     int getMaxAge();
}
