package cn.yuntangnet.duizhang.modules.system.service.impl;

import cn.yuntangnet.duizhang.common.util.Constant;
import cn.yuntangnet.duizhang.exception.RtException;
import cn.yuntangnet.duizhang.modules.system.entity.SystemRole;
import cn.yuntangnet.duizhang.modules.system.mapper.SystemRoleMapper;
import cn.yuntangnet.duizhang.modules.system.service.ISystemRoleMenuService;
import cn.yuntangnet.duizhang.modules.system.service.ISystemRoleService;
import cn.yuntangnet.duizhang.modules.system.service.ISystemUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements ISystemRoleService {

    private final ISystemRoleMenuService systemRoleMenuService;
    private final ISystemUserService iSystemUserService;

    @Autowired
    public SystemRoleServiceImpl(ISystemRoleMenuService systemRoleMenuService, ISystemUserService iSystemUserService) {
        this.systemRoleMenuService = systemRoleMenuService;
        this.iSystemUserService = iSystemUserService;
    }

    /**
     * 保存一个角色
     *
     * @param role SystemRole
     */
    @Override
    @Transactional
    public void save(SystemRole role) {
        role.setCreateTime(new Date());
        insert(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        systemRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    /**
     * 更新一个角色
     *
     * @param role SystemRole
     */
    @Override
    @Transactional
    public void update(SystemRole role) {
        updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        systemRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    /**
     * 删除角色信息
     *
     * @param roleIds Long[]
     */
    @Override
    @Transactional
    public void deleteBatch(Long[] roleIds) {
        baseMapper.deleteBatch(roleIds);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(SystemRole role) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if (role.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = iSystemUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new RtException("新增角色的权限，已超出你的权限范围");
        }
    }
}
