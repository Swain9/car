package cn.yuntangnet.duizhang.modules.business.controller;


import cn.yuntangnet.duizhang.common.annotation.SystemLogAnnotation;
import cn.yuntangnet.duizhang.common.util.BootstrapPageBean;
import cn.yuntangnet.duizhang.common.util.Constant;
import cn.yuntangnet.duizhang.common.util.PageInfo;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.common.validator.ValidatorUtils;
import cn.yuntangnet.duizhang.common.validator.group.AddGroup;
import cn.yuntangnet.duizhang.common.validator.group.UpdateGroup;
import cn.yuntangnet.duizhang.modules.business.entity.BusinessContent;
import cn.yuntangnet.duizhang.modules.business.service.IBusinessContentService;
import cn.yuntangnet.duizhang.modules.system.controller.AbstractController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 茂林
 * @since 2018-02-24
 */
@RestController
@RequestMapping("/business/content")
public class BusinessContentController extends AbstractController {

    private final IBusinessContentService businessContentService;

    @Autowired
    public BusinessContentController(IBusinessContentService businessContentService) {
        this.businessContentService = businessContentService;
    }

    @RequestMapping("/list")
    @RequiresPermissions("business:content:list")
    public ResultBean list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageInfo<BusinessContent> pageInfo = new PageInfo<>(params);
        EntityWrapper<BusinessContent> wrapper = new EntityWrapper<>();

        String theme = (String) params.get("theme");

        if (StringUtils.isNotBlank(theme)) {
            wrapper.like("content_theme", theme);
        }

        Page<BusinessContent> page = businessContentService.selectPage(pageInfo, wrapper);
        BootstrapPageBean pageBean = new BootstrapPageBean(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent());

        return ResultBean.ok(pageBean);
    }

    @SystemLogAnnotation("新增一个活动")
    @RequestMapping("/save")
    @RequiresPermissions("business:content:save")
    public ResultBean save(@RequestBody BusinessContent businessContent) {
        //校验数据
        ValidatorUtils.validateEntity(businessContent, AddGroup.class);

        Date date = new Date();
        businessContent.setIsDeleted(Constant.DataStatus.ENABLED.getValue());
        businessContent.setAdminName(getUser().getUsername());
        businessContent.setGmtCreate(date);
        businessContent.setGmtModified(date);

        businessContentService.insert(businessContent);

        return ResultBean.ok();
    }

    @RequestMapping("/info/{id}")
    @RequiresPermissions("business:content:info")
    public ResultBean info(@PathVariable Long id) {

        BusinessContent businessContent = businessContentService.selectById(id);

        return ResultBean.ok(businessContent);
    }

    @SystemLogAnnotation("更新一个活动")
    @RequestMapping("/update")
    @RequiresPermissions("business:content:update")
    public ResultBean update(@RequestBody BusinessContent businessContent) {
        //校验数据
        ValidatorUtils.validateEntity(businessContent, UpdateGroup.class);

        Long id = businessContent.getId();
        BusinessContent selectBusinessContent = businessContentService.selectById(id);
        if (selectBusinessContent.getIsDeleted() == Constant.DataStatus.DISABLED.getValue()) {
            return ResultBean.error("该数据已删除");
        }
        if (!selectBusinessContent.getContentStatus().equals(Constant.ContentStatus.UNSTART.getValue())) {
            return ResultBean.error("禁止修改已开启,已关闭或已结束的活动");
        }

        selectBusinessContent.setContentTeacher(businessContent.getContentTeacher());
        selectBusinessContent.setContentTheme(businessContent.getContentTheme());
        selectBusinessContent.setContentMoney(businessContent.getContentMoney());
        selectBusinessContent.setContentDetails(businessContent.getContentDetails());
        selectBusinessContent.setBeginTime(businessContent.getBeginTime());
        selectBusinessContent.setEndTime(businessContent.getEndTime());
        selectBusinessContent.setAdminName(getUser().getUsername());
        selectBusinessContent.setGmtModified(new Date());

        businessContentService.updateById(selectBusinessContent);

        return ResultBean.ok();
    }
}

