package cn.yuntangnet.duizhang.exception;

import cn.yuntangnet.duizhang.common.util.ResultBean;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author 茂林
 * @since 2017/11/21 10:31
 */
@RestControllerAdvice
public class GlobalExceptionHandle {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    /**
     * 自定义异常处理
     *
     * @param e RtException
     * @return ResultBean
     */
    @ExceptionHandler(RtException.class)
    public ResultBean handleRtException(RtException e) {
        logger.error(e.getMessage(), e);
        return ResultBean.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResultBean handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return ResultBean.error("数据库中已存在该记录");
    }

    @ExceptionHandler(ShiroException.class)
    public ResultBean handleAuthorizationException(ShiroException e) {
        logger.error(e.getMessage(), e);
        String msg = "未知错误";
        if (e instanceof AuthorizationException) {
            msg = "没有权限，请联系管理员授权";
        } else if (e instanceof IncorrectCredentialsException) {
            msg = "账号密码错误";
        } else if(e instanceof UnknownAccountException) {
            msg = "账号密码错误";
        }
        return ResultBean.error(msg);
    }

    @ExceptionHandler(Exception.class)
    public ResultBean handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultBean.error();
    }
}
