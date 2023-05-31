package com.vz.shiro.demo.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vz.shiro.demo.common.Result;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 *
 */
public class AjaxAuthorizationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        int httpStatus;
        HttpServletResponse res = (HttpServletResponse) response;
        try (OutputStream os = res.getOutputStream()){
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            res.setStatus(httpStatus);
            res.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            Result<?> result = Result.error(httpStatus, "没有权限");
            String body = JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
            os.write(body.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
