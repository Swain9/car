package cn.yuntangnet.duizhang.modules.system.service;

import cn.yuntangnet.duizhang.modules.system.entity.SystemMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
public interface ISystemMenuService extends IService<SystemMenu> {

    /**
     * 获取用户菜单列表
     *
     * @param userId 用户ID
     * @return List<SystemMenu>
     */
    List<SystemMenu> getUserMenuList(Long userId);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     * @return List<SystemMenu>
     */
    List<SystemMenu> queryListParentId(Long parentId);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     * @return List<SystemMenu>
     */
    List<SystemMenu> queryListParentId(Long parentId, List<Long> menuIdList);
}
