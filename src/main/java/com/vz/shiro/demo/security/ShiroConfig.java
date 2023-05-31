package com.vz.shiro.demo.security;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author visy.wang
 * @description: Shiro配置
 * @date 2023/5/30 15:03
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置一个自定义的领域（Realm），用于用户的认证和授权
     */
    @Bean
    public Realm userRealm(CredentialsMatcher credentialsMatcher) {
        UserRealm realm = new UserRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    /**
     * 密码比较器
     */
    @Bean
    public CredentialsMatcher credentialsMatcher(){
        return new UserCredentialsMatcher();
    }

    /**
     * 配置Shiro的安全管理器
     */
    @Bean
    public WebSecurityManager securityManager(Realm userRealm,
                                              SessionManager sessionManager,
                                              EhCacheManager ehCacheManager) {
        //使用默认的安全管理器，并设置自定义的领域对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * 自定义Session管理器
     * 可解决Cookie被禁用的问题，由前端主动传参
     */
    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new MySessionManager();
        //设置Session有效期：2h
        sessionManager.setGlobalSessionTimeout(2*60*60*1000);
        return sessionManager;
    }

    /**
     * 配置缓存管理
     * 用于缓存授权信息
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

    /**
     * 配置Shiro过滤器，设置相关拦截规则
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(WebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("authc", new AjaxAuthorizationFilter());
        shiroFilter.setFilters(filters);

        //设置安全管理器
        shiroFilter.setSecurityManager(securityManager);
        //登录地址，需自定义该接口
        shiroFilter.setLoginUrl("/auth/login");
        //登录成功后跳转地址
        shiroFilter.setSuccessUrl("/");
        //没有权限时跳转地址
        shiroFilter.setUnauthorizedUrl("/auth/unauthorized");
        //有先后顺序
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/auth/login", "anon");//登录接口放行
        filterMap.put("/auth/logout", "anon");//自定义退出接口，需放行
        //filterMap.put("/auth/logout", "logout");// 退出登录，这种方式下不需要自定义退出接口，Shiro会自动退出
        filterMap.put("/test/notLogin", "anon");// 允许不登陆访问
        filterMap.put("/**", "authc");// 进行身份认证后才能访问
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     * 开启Shiro注解模式，可以在Controller中的方法上添加注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(WebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 开启cglib代理，不然权限注解不会生效
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
