package cn.yuntangnet.duizhang.modules.system.service;

import cn.yuntangnet.duizhang.modules.system.entity.SystemUserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
public interface ISystemUserRoleService extends IService<SystemUserRole> {
    /**
     * 更新角色与菜单关系
     *
     * @param userId
     * @param roleIdList
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);
}
