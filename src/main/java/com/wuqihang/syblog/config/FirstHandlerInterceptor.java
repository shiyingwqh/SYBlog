package com.wuqihang.syblog.config;

import com.wuqihang.syblog.services.AccountService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wuqihang
 */
@Component
public class FirstHandlerInterceptor implements HandlerInterceptor {
    private boolean first = true;
    private final AccountService accountService;

    public FirstHandlerInterceptor(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!first || !accountService.getAllAccount().isEmpty()) {
            first = false;
            return true;
        }
        response.sendRedirect("/admin/signup");
        return false;
    }
}
