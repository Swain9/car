package cn.yuntangnet.duizhang.modules.users.controller;

import cn.yuntangnet.duizhang.common.annotation.SystemLogAnnotation;
import cn.yuntangnet.duizhang.common.util.BootstrapPageBean;
import cn.yuntangnet.duizhang.common.util.Constant;
import cn.yuntangnet.duizhang.common.util.PageInfo;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.common.validator.ValidatorUtils;
import cn.yuntangnet.duizhang.common.validator.group.AddGroup;
import cn.yuntangnet.duizhang.common.validator.group.UpdateGroup;
import cn.yuntangnet.duizhang.exception.RtException;
import cn.yuntangnet.duizhang.modules.system.controller.AbstractController;
import cn.yuntangnet.duizhang.modules.users.entity.UserBase;
import cn.yuntangnet.duizhang.modules.users.service.IUserBaseService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 张茂林
 * @since 2017/12/8 15:19
 */
@RestController
@RequestMapping("/users/base")
public class UserBaseController extends AbstractController {


    private final IUserBaseService userBaseService;

    @Autowired
    public UserBaseController(IUserBaseService userBaseService) {
        this.userBaseService = userBaseService;
    }

    @RequestMapping("/list")
    @RequiresPermissions("users:base:list")
    public ResultBean list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageInfo<UserBase> pageInfo = new PageInfo<>(params);
        EntityWrapper<UserBase> wrapper = new EntityWrapper<>();
        String username = (String) params.get("username");

        if (StringUtils.isNotBlank(username)) {
            wrapper.like("user_name", username);
        }
        Page<UserBase> page = userBaseService.selectPage(pageInfo, wrapper);
        BootstrapPageBean pageBean = new BootstrapPageBean(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent());

        return ResultBean.ok(pageBean);
    }

    @SystemLogAnnotation("新增一个用户")
    @RequestMapping("/save")
    @RequiresPermissions("users:base:save")
    public ResultBean save(@RequestBody UserBase userBase) {
        //校验数据
        ValidatorUtils.validateEntity(userBase, AddGroup.class);

        userBase.setUserFrom(Constant.UserFrom.HOUTAI.getValue());
        userBase.setUserEmail(null);
        Date date = new Date();
        userBase.setGmtCreate(date);
        userBase.setGmtModified(date);

        userBaseService.insert(userBase);
        return ResultBean.ok();
    }

    @RequestMapping("/info/{id}")
    @RequiresPermissions("users:base:info")
    public ResultBean info(@PathVariable Long id) {

        UserBase userBase = userBaseService.selectById(id);

        return ResultBean.ok(userBase);
    }

    @SystemLogAnnotation("更新一个用户")
    @RequestMapping("/update")
    @RequiresPermissions("users:base:update")
    public ResultBean update(@RequestBody UserBase userBase) {
        //校验数据
        ValidatorUtils.validateEntity(userBase, UpdateGroup.class);

        userBase.setUserName(null);
        userBase.setUserPhone(null);
        userBase.setUserParentId(null);
        userBase.setUserEmail(null);
        Date date = new Date();
        userBase.setGmtModified(date);

        userBaseService.updateById(userBase);
        return ResultBean.ok();
    }

    @SystemLogAnnotation("禁用用户")
    @RequestMapping("/disabled")
    @RequiresPermissions("users:base:disabled")
    public ResultBean disabled(@RequestBody Long[] ids) {
        List<UserBase> list = getList(ids, Constant.DataStatus.DISABLED.getValue());

        userBaseService.updateBatchById(list);
        return ResultBean.ok();
    }

    @SystemLogAnnotation("启用用户")
    @RequestMapping("/enabled")
    @RequiresPermissions("users:base:enabled")
    public ResultBean enabled(@RequestBody Long[] ids) {
        List<UserBase> list = getList(ids, Constant.DataStatus.ENABLED.getValue());

        userBaseService.updateBatchById(list);
        return ResultBean.ok();
    }

    private List<UserBase> getList(Long[] ids, int status) {
        if (ArrayUtils.isEmpty(ids)) {
            throw new RtException("请选择要操作的数据", 401);
        }
        List<UserBase> list = new ArrayList<>();
        Date date = new Date();
        for (Long id : ids) {
            UserBase userBase = new UserBase();
            userBase.setId(id);
            userBase.setIsDeleted(status);
            userBase.setGmtModified(date);
            list.add(userBase);
        }
        return list;
    }
}
