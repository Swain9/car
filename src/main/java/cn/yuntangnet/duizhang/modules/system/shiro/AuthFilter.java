package cn.yuntangnet.duizhang.modules.system.shiro;

import cn.yuntangnet.duizhang.common.util.JsonUtils;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 茂林
 * @since 2017/11/29 17:55
 */
public class AuthFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String header = request.getHeader("X-Requested-With");

        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            ResultBean error = ResultBean.error(HttpStatus.UNAUTHORIZED.value(), "请登陆");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtils.objectToJson(error));
        } else {
            this.setLoginUrl("/login.html");
            this.saveRequestAndRedirectToLogin(servletRequest, servletResponse);
        }

        return false;
    }
}
