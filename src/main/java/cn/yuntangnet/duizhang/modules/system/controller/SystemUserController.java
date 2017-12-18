package cn.yuntangnet.duizhang.modules.system.controller;


import cn.yuntangnet.duizhang.common.annotation.SystemLogAnnotation;
import cn.yuntangnet.duizhang.common.util.BootstrapPageBean;
import cn.yuntangnet.duizhang.common.util.Constant;
import cn.yuntangnet.duizhang.common.util.PageInfo;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.common.validator.Assert;
import cn.yuntangnet.duizhang.common.validator.ValidatorUtils;
import cn.yuntangnet.duizhang.common.validator.group.AddGroup;
import cn.yuntangnet.duizhang.common.validator.group.UpdateGroup;
import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import cn.yuntangnet.duizhang.modules.system.entity.SystemUserRole;
import cn.yuntangnet.duizhang.modules.system.service.ISystemUserRoleService;
import cn.yuntangnet.duizhang.modules.system.service.ISystemUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/system/user")
public class SystemUserController extends AbstractController {

    private final ISystemUserService systemUserService;
    private final ISystemUserRoleService systemUserRoleService;

    @Autowired
    public SystemUserController(ISystemUserService systemUserService, ISystemUserRoleService systemUserRoleService) {
        this.systemUserService = systemUserService;
        this.systemUserRoleService = systemUserRoleService;
    }

    /**
     * 获取登录的用户信息
     *
     * @return
     */
    @RequestMapping("/info")
    public ResultBean info() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", getUser());
        return ResultBean.ok(map);
    }

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public ResultBean list(@RequestParam Map<String, Object> params) {

        //查询列表数据
        PageInfo<SystemUser> pageInfo = new PageInfo<>(params);
        //只有超级管理员，才能查看所有管理员列表
        EntityWrapper<SystemUser> wrapper = new EntityWrapper<>();
        if (getUserId() != Constant.SUPER_ADMIN) {
            wrapper.eq("create_user_id", getUserId());
        }
        String username = (String) params.get("username");
        if (StringUtils.isNotBlank(username)) {
            wrapper.like("username", username);
        }
        Page<SystemUser> page = systemUserService.selectPage(pageInfo, wrapper);
        BootstrapPageBean pageBean = new BootstrapPageBean(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent());

        return ResultBean.ok(pageBean);
    }

    /**
     * 修改登录用户密码
     */
    @SystemLogAnnotation("修改密码")
    @RequestMapping("/password")
    public ResultBean password(String password, String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");

        //sha256加密
        password = new Sha256Hash(password, getUser().getSalt()).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword, getUser().getSalt()).toHex();

        SystemUser user = new SystemUser();
        user.setUserId(getUserId());
        user.setPassword(newPassword);
        //更新密码
        EntityWrapper<SystemUser> wrapper = new EntityWrapper<>();
        wrapper.eq("password", password);
        boolean update = systemUserService.update(user, wrapper);
        if (!update) {
            return ResultBean.error("原密码不正确");
        }
        return ResultBean.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public ResultBean info(@PathVariable("userId") Long userId) {
        SystemUser user = systemUserService.selectById(userId);

        //获取用户所属的角色列表
        EntityWrapper<SystemUserRole> wrapper = new EntityWrapper<>();
        //select role_id from sys_user_role where user_id = #{value}
        wrapper.setSqlSelect("role_id").eq("user_id", userId);

        List<Object> roleIdList = systemUserRoleService.selectObjs(wrapper);

        user.setRoleIdList((List<Long>) (List) roleIdList);

        return ResultBean.ok(user);
    }

    /**
     * 保存用户
     */
    @SystemLogAnnotation("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public ResultBean save(@RequestBody SystemUser user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);

        user.setCreateUserId(getUserId());
        systemUserService.save(user);

        return ResultBean.ok();
    }

    /**
     * 修改用户
     */
    @SystemLogAnnotation("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public ResultBean update(@RequestBody SystemUser user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        user.setCreateUserId(getUserId());
        systemUserService.update(user);

        return ResultBean.ok();
    }

    /**
     * 删除用户
     */
    @SystemLogAnnotation("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public ResultBean delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return ResultBean.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return ResultBean.error("当前用户不能删除");
        }

        systemUserService.deleteBatch(userIds);

        return ResultBean.ok();
    }
}

