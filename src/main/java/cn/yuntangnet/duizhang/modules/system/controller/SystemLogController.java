package cn.yuntangnet.duizhang.modules.system.controller;


import cn.yuntangnet.duizhang.common.util.BootstrapPageBean;
import cn.yuntangnet.duizhang.common.util.PageInfo;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.modules.system.entity.SystemLog;
import cn.yuntangnet.duizhang.modules.system.service.ISystemLogService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/system/log")
public class SystemLogController extends AbstractController {

    private final ISystemLogService systemLogService;

    @Autowired
    public SystemLogController(ISystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:log:list")
    public ResultBean list(@RequestParam Map<String, Object> params) {

        PageInfo<SystemLog> pageInfo = new PageInfo<>(params);
        EntityWrapper<SystemLog> wrapper = new EntityWrapper<>();
        if (params.get("key") != null) {
            String key = (String) params.get("key");
            wrapper.like("username", key).or().like("operation", key);
        }
        //查询列表数据
        Page<SystemLog> page = systemLogService.selectPage(pageInfo, wrapper);
        //return new ResultBean(new EasyUIPageBean(list.getTotal(), list.getRecords()));
        BootstrapPageBean pageBean = new BootstrapPageBean(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent());

        return new ResultBean(pageBean);
    }
}

