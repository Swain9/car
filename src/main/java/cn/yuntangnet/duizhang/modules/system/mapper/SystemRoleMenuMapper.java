package cn.yuntangnet.duizhang.modules.system.mapper;

import cn.yuntangnet.duizhang.modules.system.entity.SystemRoleMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 角色与菜单对应关系 Mapper 接口
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
public interface SystemRoleMenuMapper extends BaseMapper<SystemRoleMenu> {

    /**
     * 添加一个
     * @param map
     */
    void save(Map<String, Object> map);
}
