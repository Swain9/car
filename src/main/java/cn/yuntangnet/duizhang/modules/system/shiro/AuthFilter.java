package cn.yuntangnet.duizhang.modules.system.shiro;

import cn.yuntangnet.duizhang.common.util.JsonUtils;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
        ResultBean error = ResultBean.error(HttpStatus.UNAUTHORIZED.value(), "请登陆");
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setContentType("application/json;charset=UTF-8");
        httpResponse.getWriter().write(JsonUtils.objectToJson(error));
        return false;
    }
}
