package cn.yuntangnet.duizhang.modules.system.service.impl;

import cn.yuntangnet.duizhang.common.util.Constant;
import cn.yuntangnet.duizhang.exception.RtException;
import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import cn.yuntangnet.duizhang.modules.system.mapper.SystemRoleMapper;
import cn.yuntangnet.duizhang.modules.system.mapper.SystemUserMapper;
import cn.yuntangnet.duizhang.modules.system.service.ISystemUserRoleService;
import cn.yuntangnet.duizhang.modules.system.service.ISystemUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

    private final ISystemUserRoleService systemUserRoleService;
    private final SystemRoleMapper systemRoleMapper;

    @Autowired
    public SystemUserServiceImpl(ISystemUserRoleService systemUserRoleService, SystemRoleMapper systemRoleMapper) {
        this.systemUserRoleService = systemUserRoleService;
        this.systemRoleMapper = systemRoleMapper;
    }

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId 用户ID
     * @return List<Long>
     */
    @Override
    public List<Long> queryAllMenuId(Long userId) {
        List<Long> list = baseMapper.queryAllMenuId(userId);
        return list;
        //return null;
    }

    /**
     * 保存一个用户信息
     *
     * @param user
     */
    @Override
    @Transactional
    public void save(SystemUser user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        insert(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        systemUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    /**
     * 更新一个用户信息
     *
     * @param user
     */
    @Override
    @Transactional
    public void update(SystemUser user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        updateById(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        systemUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    /**
     * 删除用户信息
     *
     * @param userIds
     */
    @Override
    public void deleteBatch(Long[] userIds) {
        baseMapper.deleteBatch(userIds);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SystemUser user) {
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //todo [循环依赖] 查询用户创建的角色列表
        //List<Long> roleIdList = systemRoleService.queryRoleIdList(user.getCreateUserId());
        List<Long> roleIdList = systemRoleMapper.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new RtException("新增用户所选角色，不是本人创建");
        }
    }
}
