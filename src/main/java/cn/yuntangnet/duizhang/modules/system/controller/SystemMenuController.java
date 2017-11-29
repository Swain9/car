package cn.yuntangnet.duizhang.modules.system.controller;


import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.modules.system.entity.SystemMenu;
import cn.yuntangnet.duizhang.modules.system.service.ISystemMenuService;
import cn.yuntangnet.duizhang.modules.system.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/system/menu")
public class SystemMenuController extends AbstractController {


    private final ISystemMenuService systemMenuService;
    private final ShiroService shiroService;

    @Autowired
    public SystemMenuController(ISystemMenuService systemMenuService, ShiroService shiroService) {
        this.systemMenuService = systemMenuService;
        this.shiroService = shiroService;
    }

    /**
     * 导航菜单
     *
     * @return
     */
    @PostMapping("/nav")
    public ResultBean nav() {
        List<SystemMenu> menuList = systemMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        //return R.ok().put("menuList", menuList).put("permissions", permissions);
        //List<SystemMenu> menuList = systemMenuService.getUserMenuList(1L);
        //Set<String> permissions = shiroService.getUserPermissions(1L);
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", menuList);
        map.put("permissions", permissions);

        return ResultBean.ok(map);
    }
}

