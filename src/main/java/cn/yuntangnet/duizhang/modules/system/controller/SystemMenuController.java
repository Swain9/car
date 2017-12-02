package cn.yuntangnet.duizhang.modules.system.controller;


import cn.yuntangnet.duizhang.common.annotation.SystemLogAnnotation;
import cn.yuntangnet.duizhang.common.util.Constant.MenuType;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.exception.RtException;
import cn.yuntangnet.duizhang.modules.system.entity.SystemMenu;
import cn.yuntangnet.duizhang.modules.system.service.ISystemMenuService;
import cn.yuntangnet.duizhang.modules.system.service.ShiroService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        //return ResultBean.ok().put("menuList", menuList).put("permissions", permissions);
        //List<SystemMenu> menuList = systemMenuService.getUserMenuList(1L);
        //Set<String> permissions = shiroService.getUserPermissions(1L);
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", menuList);
        map.put("permissions", permissions);

        return ResultBean.ok(map);
    }

    /**
     * 查询所有菜单列表信息
     *
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<SystemMenu> list() {
        List<SystemMenu> menuList = systemMenuService.selectList(null);

        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     *
     * @return
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public ResultBean select() {
        //select * from sys_menu where type != 2 order by order_num asc
        //查询列表数据
        EntityWrapper<SystemMenu> wrapper = new EntityWrapper<>();
        wrapper.ne("type", 2).orderBy("order_num", true);
        List<SystemMenu> menuList = systemMenuService.selectList(wrapper);

        //添加顶级菜单
        SystemMenu root = new SystemMenu();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return ResultBean.ok(menuList);
    }

    /**
     * 菜单信息
     *
     * @param menuId 菜单主键ID
     * @return
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public ResultBean info(@PathVariable("menuId") Long menuId) {
        SystemMenu menu = systemMenuService.selectById(menuId);

        return ResultBean.ok(menu);
    }

    /**
     * 保存菜单
     *
     * @param menu 菜单实体类
     * @return
     */
    @SystemLogAnnotation("保存菜单")
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public ResultBean save(@RequestBody SystemMenu menu) {
        //数据校验
        verifyForm(menu);

        systemMenuService.insert(menu);

        return ResultBean.ok();
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单对象
     * @return
     */
    @SystemLogAnnotation("修改菜单")
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public ResultBean update(@RequestBody SystemMenu menu) {
        //数据校验
        verifyForm(menu);

        systemMenuService.updateById(menu);

        return ResultBean.ok();
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     * @return
     */
    @SystemLogAnnotation("删除菜单")
    @PostMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public ResultBean delete(Long menuId) {
        //判断是否有子菜单或按钮
        List<SystemMenu> menuList = systemMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return ResultBean.error("请先删除子菜单或按钮");
        }

        systemMenuService.deleteMenuById(menuId);

        return ResultBean.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SystemMenu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new RtException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new RtException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new RtException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SystemMenu parentMenu = systemMenuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == MenuType.CATALOG.getValue() ||
                menu.getType() == MenuType.MENU.getValue()) {
            if (parentType != MenuType.CATALOG.getValue()) {
                throw new RtException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == MenuType.BUTTON.getValue()) {
            if (parentType != MenuType.MENU.getValue()) {
                throw new RtException("上级菜单只能为菜单类型");
            }
            return;
        }
    }

}

