package cn.yuntangnet.duizhang.modules.system.controller;

import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 茂林
 * @since 2017/11/22 18:36
 */
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SystemUser getUser() {
        return (SystemUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
