package cn.yuntangnet.duizhang.modules.system.service.impl;

import cn.yuntangnet.duizhang.common.util.Constant;
import cn.yuntangnet.duizhang.common.util.Constant.MenuType;
import cn.yuntangnet.duizhang.modules.system.entity.SystemMenu;
import cn.yuntangnet.duizhang.modules.system.entity.SystemRoleMenu;
import cn.yuntangnet.duizhang.modules.system.mapper.SystemMenuMapper;
import cn.yuntangnet.duizhang.modules.system.service.ISystemMenuService;
import cn.yuntangnet.duizhang.modules.system.service.ISystemRoleMenuService;
import cn.yuntangnet.duizhang.modules.system.service.ISystemUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements ISystemMenuService {

    private final ISystemUserService systemUserService;

    private final ISystemRoleMenuService systemRoleMenuService;

    @Autowired
    public SystemMenuServiceImpl(ISystemUserService systemUserService, ISystemRoleMenuService systemRoleMenuService) {
        this.systemUserService = systemUserService;
        this.systemRoleMenuService = systemRoleMenuService;
    }


    /**
     * 获取用户菜单列表
     *
     * @param userId 用户ID
     * @return List<SystemMenu>
     */
    @Override
    public List<SystemMenu> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = systemUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     *
     * @param menuIdList 用户菜单ID列表
     * @return List<SystemMenu>
     */
    private List<SystemMenu> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SystemMenu> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    private List<SystemMenu> getMenuTreeList(List<SystemMenu> menuList, List<Long> menuIdList) {
        List<SystemMenu> subMenuList = new ArrayList<>();

        for (SystemMenu entity : menuList) {
            if (entity.getType() == MenuType.CATALOG.getValue()) {//目录
                List<SystemMenu> list = queryListParentId(entity.getMenuId(), menuIdList);
                List<SystemMenu> treeList = getMenuTreeList(list, menuIdList);
                entity.setList(treeList);
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     * @return List<SystemMenu>
     */
    @Override
    public List<SystemMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
        //查询所有的子菜单
        List<SystemMenu> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }
        //
        List<SystemMenu> userMenuList = new ArrayList<>();
        for (SystemMenu menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 根据菜单ID删除子菜单
     *
     * @param menuId
     */
    @Override
    @Transactional
    public void deleteMenuById(Long menuId) {
        //根据主键删除菜单数据
        deleteById(menuId);
        //删除角色菜单关联信息中的菜单数据
        EntityWrapper<SystemRoleMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("menu_id", menuId);
        systemRoleMenuService.delete(wrapper);
    }

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     * @return List<SystemMenu>
     */
    @Override
    public List<SystemMenu> queryListParentId(Long parentId) {

        List<SystemMenu> list = baseMapper.selectList(
                new EntityWrapper<SystemMenu>()
                        .eq("parent_id", parentId)
                        .orderBy("order_num", true)
        );
        return list;
        //selectList()
        //return systemMenuMapper.queryListParentId(parentId);
    }
}
