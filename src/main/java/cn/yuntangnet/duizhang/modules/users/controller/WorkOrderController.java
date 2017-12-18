package cn.yuntangnet.duizhang.modules.users.controller;

import cn.yuntangnet.duizhang.common.annotation.SystemLogAnnotation;
import cn.yuntangnet.duizhang.common.util.BootstrapPageBean;
import cn.yuntangnet.duizhang.common.util.PageInfo;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.modules.users.entity.WorkOrder;
import cn.yuntangnet.duizhang.modules.users.service.IWorkOrderService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author 张茂林
 * @since 2017/12/17 19:37
 */
@RestController
@RequestMapping("/users/wo")
public class WorkOrderController {

    @Autowired
    private IWorkOrderService workOrderService;

    @RequestMapping("/list")
    @RequiresPermissions("users:wo:list")
    public ResultBean list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageInfo<WorkOrder> pageInfo = new PageInfo<>(params);
        EntityWrapper<WorkOrder> wrapper = new EntityWrapper<>();

        String key = (String) params.get("key");
        String orderStatus = (String) params.get("orderStatus");
        if (StringUtils.isNotBlank(orderStatus)) {
            wrapper.eq("order_status", orderStatus);
        }

        if (StringUtils.isNotBlank(key)) {
            wrapper.and().like("agent_area", key).or().like("agent_name", key).or().like("user_phone", key);
        }

        Page<WorkOrder> page = workOrderService.selectPage(pageInfo, wrapper);
        BootstrapPageBean pageBean = new BootstrapPageBean(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent());

        return ResultBean.ok(pageBean);
    }

    @RequestMapping("/info/{id}")
    @RequiresPermissions("users:wo:info")
    public ResultBean info(@PathVariable Long id) {

        WorkOrder workOrder = workOrderService.selectById(id);

        return ResultBean.ok(workOrder);
    }

    @SystemLogAnnotation("处理一个工单")
    @RequestMapping("/update")
    @RequiresPermissions("users:wo:update")
    public ResultBean update(@RequestBody WorkOrder workOrder) {

        WorkOrder exitWo = workOrderService.selectById(workOrder.getId());
        if (exitWo != null && "1".equals(exitWo.getOrderStatus())) {
            exitWo.setMsg(workOrder.getMsg());
            exitWo.setOrderStatus("2");
            exitWo.setGmtModified(new Date());
            workOrderService.updateById(exitWo);
        }

        return ResultBean.ok();
    }


}
