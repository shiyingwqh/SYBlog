package com.wuqihang.syblog.security;

import com.wuqihang.syblog.config.SYConfiguration;
import com.wuqihang.syblog.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author Wuqihang
 */
@Component
@EnableConfigurationProperties(SYConfiguration.class)
public class TokenManagerImpl implements TokenManager {
    private final Map<String, Token> tokenMap;
    private final Map<String, Token> unameTokenMap;
    @Value("#{SYConfiguration.tokenKeepTime}")
    private int tokenKeepTime;
    @Value("#{SYConfiguration.bytes}")
    private Byte[] bytes;
    @Value("#{SYConfiguration.key}")
    private String key;
    private final Logger logger = LoggerFactory.getLogger(TokenManager.class);

    public TokenManagerImpl() {
        tokenMap = new Hashtable<>();
        unameTokenMap = new Hashtable<>();
    }

    @Override
    public void putToken(Token token) {
        if (!tokenMap.containsKey(token.token)) {
            tokenMap.put(token.token, token);
        }
    }

    @Override
    public Token createToken(User user) {
        if (unameTokenMap.containsKey(user.getUsername())) {
            String t = unameTokenMap.get(user.getUsername()).getToken();
            unameTokenMap.remove(user.getUsername());
            tokenMap.remove(t);
        }
        Token token = new Token();
        token.userId = user.getId();
        token.createdTime = System.currentTimeMillis();
        String username = user.getUsername();
        String password = user.getPassword();
        String u = token.createdTime + username + password + key;
        byte[] bytes = u.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < this.bytes.length; i++) {
            bytes[i] = this.bytes[i];
        }
        token.token = DigestUtils.md5DigestAsHex(bytes);
        token.user = user;
        logger.info("Token Created:" + token.token);
        putToken(token);
        return token;
    }

    @Override
    public void deleteToken(Token token) {
        tokenMap.remove(token.token);
        unameTokenMap.remove(token.user.getUsername());
    }

    @Override
    public void deleteToken(String token) {
        if (token == null){
            return;
        }
        Token token1 = tokenMap.get(token);
        if (token1 == null){
            return;
        }
        unameTokenMap.remove(token1.user.getUsername());
        tokenMap.remove(token);
    }

    @Override
    public boolean checkToken(String token) {
        return token != null && !token.equals("") && tokenMap.containsKey(token) && System.currentTimeMillis() - tokenMap.get(token).createdTime < (long) tokenKeepTime * 1000;
//        return true;
    }

    @Override
    public boolean checkToken(String token, HttpServletRequest request) {
        if (token == null || token.equals("")) {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) return false;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return checkToken(token);
    }

    @Override
    public int getMaxAge() {
        return tokenKeepTime;
    }
}
