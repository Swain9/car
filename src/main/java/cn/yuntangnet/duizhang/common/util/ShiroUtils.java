package cn.yuntangnet.duizhang.common.util;

import cn.yuntangnet.duizhang.exception.RtException;
import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author 茂林
 * @since 2017/11/29 16:22
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取用户
     *
     * @return
     */
    public static SystemUser getUserEntity() {
        return (SystemUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取用户ID
     *
     * @return Long
     */
    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    /**
     * 设置Session属性
     *
     * @param key   键
     * @param value 值
     */
    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 获取Session中的属性
     *
     * @param key 键
     * @return 值
     */
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 判断用户是否登陆
     *
     * @return boolean
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    /**
     * 获取验证码
     *
     * @param key
     * @return
     */
    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if (kaptcha == null) {
            throw new RtException("验证码已失效");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }

    /**
     * 用户登陆
     *
     * @param token
     */
    public static void login(UsernamePasswordToken token) {
        getSubject().login(token);
    }
}
