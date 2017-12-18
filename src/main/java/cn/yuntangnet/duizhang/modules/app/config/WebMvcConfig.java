package cn.yuntangnet.duizhang.modules.app.config;

import cn.yuntangnet.duizhang.modules.app.interceptor.AuthorizationInterceptor;
import cn.yuntangnet.duizhang.modules.app.resolver.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * MVC配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final AuthorizationInterceptor authorizationInterceptor;
    private final LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    @Autowired
    public WebMvcConfig(AuthorizationInterceptor authorizationInterceptor, LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver) {
        this.authorizationInterceptor = authorizationInterceptor;
        this.loginUserHandlerMethodArgumentResolver = loginUserHandlerMethodArgumentResolver;
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/app/**");
    }

    /**
     * 自定义参数
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }
}