package cn.yuntangnet.duizhang.modules.system.service;

import cn.yuntangnet.duizhang.modules.system.entity.SystemRoleMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
public interface ISystemRoleMenuService extends IService<SystemRoleMenu> {
    /**
     * 更新或修改角色与菜单关联信息
     *
     * @param roleId     roleId
     * @param menuIdList menuIdList
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);
}
