package com.wuqihang.syblog.config;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.json.JsonGeneratorImpl;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.security.TokenManager;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wuqihang
 */
@Component
public class SYHandlerInterceptor implements HandlerInterceptor {
    private final TokenManager tokenManager;
    private final ObjectMapper mapper = new ObjectMapper();

    public SYHandlerInterceptor(TokenManager tokenManager) {
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
