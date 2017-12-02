package cn.yuntangnet.duizhang.modules.system.controller;


import cn.yuntangnet.duizhang.common.annotation.SystemLogAnnotation;
import cn.yuntangnet.duizhang.common.util.BootstrapPageBean;
import cn.yuntangnet.duizhang.common.util.PageInfo;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.common.validator.ValidatorUtils;
import cn.yuntangnet.duizhang.modules.system.entity.SystemConfig;
import cn.yuntangnet.duizhang.modules.system.service.ISystemConfigService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统配置信息表 前端控制器
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController extends AbstractController {

    private final ISystemConfigService systemConfigService;


    @Autowired
    public SystemConfigController(ISystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
    }

    /**
     * 获取参数列表
     *
     * @return ResultBean
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:config:list")
    public ResultBean list(@RequestParam Map<String, Object> params) {

        PageInfo<SystemConfig> pageInfo = new PageInfo<>(params);
        EntityWrapper<SystemConfig> wrapper = new EntityWrapper<>();
        if (params.get("key") != null) {
            String key = (String) params.get("key");
            //wrapper.where(" key LIKE {0}", key);
            wrapper.like("`key`", key);
        }
        //查询列表数据
        Page<SystemConfig> page = systemConfigService.selectPage(pageInfo, wrapper);
        //return new ResultBean(new EasyUIPageBean(list.getTotal(), list.getRecords()));
        BootstrapPageBean pageBean = new BootstrapPageBean(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent());

        return new ResultBean(pageBean);

    }

    /**
     * 根据主键ID查询一个参数信息
     *
     * @param id 主键ID
     * @return ResultBean
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public ResultBean info(@PathVariable("id") Long id) {
        SystemConfig config = systemConfigService.selectById(id);

        return ResultBean.ok(config);
    }

    /**
     * 保存系统参数
     *
     * @param config SystemConfig
     * @return ResultBean
     */
    @SystemLogAnnotation("保存系统参数")
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public ResultBean save(@RequestBody SystemConfig config) {
        ValidatorUtils.validateEntity(config);

        systemConfigService.insert(config);

        return ResultBean.ok();
    }

    /**
     * 修改系统参数
     *
     * @param config SystemConfig
     * @return ResultBean
     */
    @SystemLogAnnotation("修改系统参数")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public ResultBean update(@RequestBody SystemConfig config) {
        ValidatorUtils.validateEntity(config);

        systemConfigService.updateById(config);

        return ResultBean.ok();
    }

    /**
     * 删除系统配置
     *
     * @param ids 参数列表
     * @return ResultBean
     */
    @SystemLogAnnotation("删除系统配置")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public ResultBean delete(@RequestBody List<Long> ids) {
        systemConfigService.deleteBatchIds(ids);

        return ResultBean.ok();
    }

}

