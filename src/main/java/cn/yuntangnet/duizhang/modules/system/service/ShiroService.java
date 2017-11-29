package cn.yuntangnet.duizhang.modules.system.service;

import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import cn.yuntangnet.duizhang.modules.system.entity.SystemUserToken;

import java.util.Set;

/**
 * @author 茂林
 * @since 2017/11/28 16:48
 */
public interface ShiroService {

    /**
     * 根据用户ID，获取用户权限列表
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> getUserPermissions(Long userId);

    /**
     * 根据token查询用户,未实现
     *
     * @param token
     * @return
     */
    SystemUserToken queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     *
     * @param username 用户ID
     * @return 系统用户
     */
    SystemUser queryUser(String username);
}
