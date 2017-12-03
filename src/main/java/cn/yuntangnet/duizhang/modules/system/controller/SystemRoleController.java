package cn.yuntangnet.duizhang.modules.system.controller;


import cn.yuntangnet.duizhang.common.annotation.SystemLogAnnotation;
import cn.yuntangnet.duizhang.common.util.BootstrapPageBean;
import cn.yuntangnet.duizhang.common.util.Constant;
import cn.yuntangnet.duizhang.common.util.PageInfo;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.common.validator.ValidatorUtils;
import cn.yuntangnet.duizhang.modules.system.entity.SystemRole;
import cn.yuntangnet.duizhang.modules.system.entity.SystemRoleMenu;
import cn.yuntangnet.duizhang.modules.system.service.ISystemRoleMenuService;
import cn.yuntangnet.duizhang.modules.system.service.ISystemRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/system/role")
public class SystemRoleController extends AbstractController {


    private final ISystemRoleService systemRoleService;
    private final ISystemRoleMenuService systemRoleMenuService;

    @Autowired
    public SystemRoleController(ISystemRoleService systemRoleService, ISystemRoleMenuService systemRoleMenuService) {
        this.systemRoleService = systemRoleService;
        this.systemRoleMenuService = systemRoleMenuService;
    }

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    public ResultBean list(@RequestParam Map<String, Object> params) {

        PageInfo<SystemRole> pageInfo = new PageInfo<>(params);
        EntityWrapper<SystemRole> wrapper = new EntityWrapper<>();
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            wrapper.eq("create_user_id", getUserId());
        }
        if (params.get("roleName") != null) {
            String roleName = (String) params.get("roleName");
            wrapper.like("role_name", roleName);
        }

        //查询列表数据
        Page<SystemRole> page = systemRoleService.selectPage(pageInfo, wrapper);

        BootstrapPageBean pageBean = new BootstrapPageBean(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent());

        return ResultBean.ok(pageBean);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public ResultBean info(@PathVariable("roleId") Long roleId) {
        //todo 如果不是超级管理员，则只查询自己创建的角色列表

        SystemRole role = systemRoleService.selectById(roleId);

        //查询角色对应的菜单
        //select menu_id from sys_role_menu where role_id = #{value}
        EntityWrapper<SystemRoleMenu> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("menu_id").eq("role_id", roleId);
        List<Object> menuIdList = systemRoleMenuService.selectObjs(wrapper);
        role.setMenuIdList((List<Long>) (List) menuIdList);

        return ResultBean.ok(role);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public ResultBean select() {

        EntityWrapper<SystemRole> wrapper = new EntityWrapper<>();
        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            wrapper.eq("create_user_id", getUserId());
        }
        wrapper.orderBy("role_id");
        List<SystemRole> list = systemRoleService.selectList(wrapper);

        return ResultBean.ok(list);
    }

    /**
     * 保存角色
     */
    @SystemLogAnnotation("保存角色")
    @RequestMapping("/save")
    @RequiresPermissions("sys:role:save")
    public ResultBean save(@RequestBody SystemRole role) {

        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
        systemRoleService.save(role);

        return ResultBean.ok();
    }

    /**
     * 修改角色
     */
    @SystemLogAnnotation("修改角色")
    @RequestMapping("/update")
    @RequiresPermissions("sys:role:update")
    public ResultBean update(@RequestBody SystemRole role) {
        //todo 如果不是超级管理员，则只查询自己创建的角色列表

        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
        systemRoleService.update(role);

        return ResultBean.ok();
    }

    /**
     * 删除角色
     */
    @SystemLogAnnotation("删除角色")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public ResultBean delete(@RequestBody Long[] roleIds) {
        //todo 如果不是超级管理员，则只查询自己创建的角色列表

        systemRoleService.deleteBatch(roleIds);

        return ResultBean.ok();
    }
}

