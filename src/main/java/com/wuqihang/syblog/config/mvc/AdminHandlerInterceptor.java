package com.wuqihang.syblog.config.mvc;

import com.wuqihang.syblog.security.TokenManagerImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wuqihang
 */
@Component
public class AdminHandlerInterceptor implements HandlerInterceptor {
    private final TokenManagerImpl tokenManager;

    public AdminHandlerInterceptor(TokenManagerImpl tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!tokenManager.checkToken("", request)) {
            response.sendRedirect("/admin/sign");
            return false;
        }
        return true;
    }
}
