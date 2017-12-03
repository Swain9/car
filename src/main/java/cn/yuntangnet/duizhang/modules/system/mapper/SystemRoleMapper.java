package cn.yuntangnet.duizhang.modules.system.mapper;

import cn.yuntangnet.duizhang.modules.system.entity.SystemRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
public interface SystemRoleMapper extends BaseMapper<SystemRole> {
    /**
     * 删除角色
     *
     * @param roleIds
     */
    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     *
     * @param createUserId
     * @return
     */
    List<Long> queryRoleIdList(Long createUserId);
}
