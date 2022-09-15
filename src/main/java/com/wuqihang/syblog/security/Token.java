package com.wuqihang.syblog.security;

import com.wuqihang.syblog.pojo.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Wuqihang
 */
@Getter
@EqualsAndHashCode
public class Token {
    protected String token;
    protected String userId;
    protected long createdTime;
    protected User user;

    protected Token() {
    }

    @Override
    public String toString() {
        return token;
    }
}
