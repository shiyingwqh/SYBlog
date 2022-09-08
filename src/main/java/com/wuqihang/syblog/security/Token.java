package com.wuqihang.syblog.security;

import com.wuqihang.syblog.pojo.User;
import lombok.*;

/**
 * @author Wuqihang
 */
@Getter
@ToString
@EqualsAndHashCode
public class Token {
    protected String token;
    protected String userId;
    protected long createdTime;
    protected User user;

    protected Token() {
    }
}
