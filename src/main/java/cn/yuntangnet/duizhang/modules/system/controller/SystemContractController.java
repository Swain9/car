package cn.yuntangnet.duizhang.modules.system.controller;

import cn.yuntangnet.duizhang.common.annotation.SystemLogAnnotation;
import cn.yuntangnet.duizhang.common.util.BootstrapPageBean;
import cn.yuntangnet.duizhang.common.util.CountUtil;
import cn.yuntangnet.duizhang.common.util.PageInfo;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.modules.system.entity.SystemContract;
import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import cn.yuntangnet.duizhang.modules.system.service.ISystemContractService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 张茂林
 * @since 2018/1/13 11:38
 */
@RestController()
@RequestMapping("/system/contract")
public class SystemContractController extends AbstractController {

    @Value("${contract}")
    private String contract;

    private final ISystemContractService systemContractService;

    @Autowired
    public SystemContractController(ISystemContractService systemContractService) {
        this.systemContractService = systemContractService;
    }

    @RequestMapping("/list")
    @RequiresPermissions("system:contract:list")
    public ResultBean list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageInfo<SystemContract> pageInfo = new PageInfo<>(params);
        EntityWrapper<SystemContract> wrapper = new EntityWrapper<>();

        String key = (String) params.get("key");
        String contractStatus = (String) params.get("contractStatus");
        if (StringUtils.isNotBlank(contractStatus)) {
            wrapper.eq("contract_status", contractStatus);
        }
        if (StringUtils.isNotBlank(key)) {
            if (key.matches("\\d{1,8}")) {
                String s = CountUtil.int2AAA2(Integer.parseInt(key));
                wrapper.eq("order_id", s);
            }else{
                wrapper.like("order_id", key);
            }
        }

        Page<SystemContract> page = systemContractService.selectPage(pageInfo, wrapper);
        BootstrapPageBean pageBean = new BootstrapPageBean(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent());

        return ResultBean.ok(pageBean);
    }

    @SystemLogAnnotation("添加合同")
    @RequestMapping("/add")
    @RequiresPermissions("system:contract:add")
    public ResultBean add(int begin, int end) {
        if (begin <= 0 || end <= 0 || end < begin) {
            return ResultBean.error("请输入正确的编号");
        }

        List<String> orders = new ArrayList<>();

        for (int i = begin; i <= end; i++) {
            String s = CountUtil.int2AAA2(i);
            orders.add(s);
        }

        EntityWrapper<SystemContract> wrapper = new EntityWrapper<>();
        wrapper.in("order_id", orders);

        List<SystemContract> exit = systemContractService.selectList(wrapper);
        if (exit != null && exit.size() > 0) {
            return ResultBean.error("编号已存在");
        }

        SystemUser user = getUser();
        Date date = new Date();
        List<SystemContract> list = new ArrayList<>();
        for (String s : orders) {
            SystemContract contract = new SystemContract();
            contract.setOrderId(s);
            contract.setAdminName(user.getUsername());
            contract.setContractStatus("1");
            contract.setGmtCreate(date);
            contract.setGmtModified(date);
            list.add(contract);
        }
        systemContractService.insertBatch(list);

        return ResultBean.ok();
    }

    @RequestMapping("/info/{id}")
    @RequiresPermissions("system:contract:info")
    public ResultBean info(@PathVariable Long id) {
        SystemContract systemContract = systemContractService.selectById(id);

        return ResultBean.ok(systemContract);
    }

    @RequestMapping("/download/{type}/{id}")
    public void downloadResource(@PathVariable String type, @PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {


        SystemContract systemContract = systemContractService.selectById(id);
        String path = contract + systemContract.getOrderId().toUpperCase() + "/";

        Path file = null;
        String annex = null;
        if ("1".equals(type)) {
            annex = systemContract.getAnnex1();
        } else {
            annex = systemContract.getAnnex2();
        }
        if (StringUtils.isBlank(annex)) {
            return;
        }
        file = Paths.get(path, annex);
        if (Files.exists(file)) {
            String filename = systemContract.getUserName() + systemContract.getUserPhone() + annex.substring(annex.lastIndexOf("."));
            //String fileName = "sunny.pdf";
            //response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            //如果文件名有中文的话，进行URL编码，让中文正常显示
            try {
                response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
                Files.copy(file, response.getOutputStream());
            } catch (IOException ex) {
            }
        }
    }

    @SystemLogAnnotation("处理合同")
    @RequestMapping("/update/{type}")
    @RequiresPermissions("system:contract:update")
    public ResultBean update(@PathVariable String type, @RequestBody SystemContract systemContract) {
        Long id = systemContract.getId();
        SystemContract exit = systemContractService.selectById(id);
        if (exit == null) {
            return ResultBean.error("该合同不存在");
        }
        if (!"2".equals(exit.getContractStatus())) {
            return ResultBean.error("该合同无法处理");
        }
        if ("1".equals(type)) {
            exit.setContractStatus("3");

        } else {
            //if ("0".equals(type))
            exit.setContractStatus("1");
        }
        exit.setContractMsg(systemContract.getContractMsg());
        exit.setAdminName(getUser().getUsername());
        exit.setGmtModified(new Date());

        systemContractService.updateById(exit);

        return ResultBean.ok();

    }
}
