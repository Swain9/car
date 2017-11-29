package cn.yuntangnet.duizhang.modules.system.service.impl;

import cn.yuntangnet.duizhang.common.util.Constant;
import cn.yuntangnet.duizhang.modules.system.entity.SystemMenu;
import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import cn.yuntangnet.duizhang.modules.system.entity.SystemUserToken;
import cn.yuntangnet.duizhang.modules.system.mapper.SystemMenuMapper;
import cn.yuntangnet.duizhang.modules.system.mapper.SystemUserMapper;
import cn.yuntangnet.duizhang.modules.system.service.ShiroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 茂林
 * @since 2017/11/28 16:51
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    private final SystemMenuMapper systemMenuMapper;
    private final SystemUserMapper systemUserMapper;

    @Autowired
    public ShiroServiceImpl(SystemMenuMapper systemMenuMapper, SystemUserMapper systemUserMapper) {
        this.systemMenuMapper = systemMenuMapper;
        this.systemUserMapper = systemUserMapper;
    }

    /**
     * 根据用户ID，获取用户权限列表
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> getUserPermissions(Long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            List<SystemMenu> menuList = systemMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SystemMenu menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = systemUserMapper.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    /**
     * 根据token查询用户,未实现
     *
     * @param token
     * @return
     */
    @Override
    public SystemUserToken queryByToken(String token) {
        return null;
    }

    /**
     * 根据用户ID，查询用户
     *
     * @param username 用户ID
     * @return 系统用户
     */
    @Override
    public SystemUser queryUser(String username) {
        SystemUser user = new SystemUser();
        user.setUsername(username);
        user = systemUserMapper.selectOne(user);
        return user;
    }
}
