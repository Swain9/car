package cn.yuntangnet.duizhang.modules.system.mapper;

import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
public interface SystemUserMapper extends BaseMapper<SystemUser> {
    /**
     * 根据用户ID,查询用户的所有权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId 用户ID
     * @return 菜单ID列表
     */
    List<Long> queryAllMenuId(Long userId);
}
