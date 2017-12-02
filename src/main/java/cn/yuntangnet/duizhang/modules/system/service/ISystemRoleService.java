package cn.yuntangnet.duizhang.modules.system.service;

import cn.yuntangnet.duizhang.modules.system.entity.SystemRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
public interface ISystemRoleService extends IService<SystemRole> {
    /**
     * 保存一个角色
     *
     * @param role SystemRole
     */
    void save(SystemRole role);

    /**
     * 更新一个角色
     *
     * @param role SystemRole
     */
    void update(SystemRole role);

    /**
     * 删除角色信息
     *
     * @param roleIds Long[]
     */
    void deleteBatch(Long[] roleIds);
}
