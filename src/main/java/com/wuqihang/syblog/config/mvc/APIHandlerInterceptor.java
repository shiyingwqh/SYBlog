package com.wuqihang.syblog.config.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.security.TokenManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wuqihang
 */
@Component
public class APIHandlerInterceptor implements HandlerInterceptor {
    private final TokenManager tokenManager;
    private final ObjectMapper mapper = new ObjectMapper();

    public APIHandlerInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if(!tokenManager.checkToken(token, request)){
            String s = mapper.writeValueAsString(ResponsePKG.ERROR_TOKEN_INVALID);
            response.getWriter().write(s);
            return false;
        }
        return true;
    }
}
