package cn.yuntangnet.duizhang.modules.app.resolver;

import cn.yuntangnet.duizhang.modules.app.annotation.LoginUser;
import cn.yuntangnet.duizhang.modules.app.interceptor.AuthorizationInterceptor;
import cn.yuntangnet.duizhang.modules.users.entity.UserBase;
import cn.yuntangnet.duizhang.modules.users.service.IUserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private IUserBaseService userBaseService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserBase.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (object == null) {
            return null;
        }

        //获取用户信息
        UserBase user = userBaseService.selectById((Long) object);

        return user;
    }
}
