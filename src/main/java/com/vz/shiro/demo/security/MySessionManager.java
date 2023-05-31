package com.vz.shiro.demo.security;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @author visy.wang
 * @description: 自定义Session管理器
 * @date 2023/5/31 10:27
 */
public class MySessionManager extends DefaultWebSessionManager {
    private static final String HEADER_TOKEN = "AccessToken";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    /**
     * 自定义获取SessionId的方式（防止Cookie被禁用）
     * 默认是通过Cookie获取的（如果前端禁用Cookie则无法使用）
     * @param request 请求
     * @param response 相应
     * @return 会话ID
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //从请求头获取会话ID
        String sessionId = WebUtils.toHttp(request).getHeader(HEADER_TOKEN);
        if(StringUtils.hasText(sessionId)){
            //使用自定义方式获取的会话ID
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }else{
            //获取不到时，走默认的获取方式
            return super.getSessionId(request, response);
        }
    }
}
