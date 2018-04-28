package cn.yuntangnet.duizhang.modules.business.controller;


import cn.yuntangnet.duizhang.common.util.BootstrapPageBean;
import cn.yuntangnet.duizhang.common.util.PageInfo;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.modules.business.entity.BusinessOrder;
import cn.yuntangnet.duizhang.modules.business.service.IBusinessOrderService;
import cn.yuntangnet.duizhang.modules.system.controller.AbstractController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 茂林
 * @since 2018-02-28
 */
@RestController
@RequestMapping("/business/order")
public class BusinessOrderController extends AbstractController {

    private final IBusinessOrderService businessOrderService;

    @Autowired
    public BusinessOrderController(IBusinessOrderService businessOrderService) {
        this.businessOrderService = businessOrderService;
    }

    @RequestMapping("/list")
    @RequiresPermissions("business:order:list")
    public ResultBean list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageInfo<BusinessOrder> pageInfo = new PageInfo<>(params);
        EntityWrapper<BusinessOrder> wrapper = new EntityWrapper<>();

        String key = (String) params.get("key");

        if (StringUtils.isNotBlank(key)) {
            wrapper.like("user_name", key).or().like("user_phone", key);
        }

        Page<BusinessOrder> page = businessOrderService.selectPage(pageInfo, wrapper);
        BootstrapPageBean pageBean = new BootstrapPageBean(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent());

        return ResultBean.ok(pageBean);
    }

    @RequestMapping("/info/{id}")
    @RequiresPermissions("business:order:info")
    public ResultBean info(@PathVariable Long id) {

        BusinessOrder businessOrder = businessOrderService.selectById(id);

        return ResultBean.ok(businessOrder);
    }
}

