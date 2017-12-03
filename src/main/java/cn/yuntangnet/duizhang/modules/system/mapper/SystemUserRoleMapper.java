package cn.yuntangnet.duizhang.modules.system.mapper;

import cn.yuntangnet.duizhang.modules.system.entity.SystemUserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
public interface SystemUserRoleMapper extends BaseMapper<SystemUserRole> {
    /**
     * 保存用户与角色关系
     *
     * @param map
     */
    void save(Map<String, Object> map);
}
