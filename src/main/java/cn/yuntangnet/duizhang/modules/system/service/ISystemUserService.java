package cn.yuntangnet.duizhang.modules.system.service;

import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
public interface ISystemUserService extends IService<SystemUser> {

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId 用户ID
     * @return List<Long>
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 保存一个用户信息
     *
     * @param user
     */
    void save(SystemUser user);

    /**
     * 更新一个用户信息
     *
     * @param user
     */
    void update(SystemUser user);

    /**
     * 删除用户信息
     *
     * @param userIds
     */
    void deleteBatch(Long[] userIds);
}
