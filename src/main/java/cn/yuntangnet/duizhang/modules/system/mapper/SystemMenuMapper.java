package cn.yuntangnet.duizhang.modules.system.mapper;

import cn.yuntangnet.duizhang.modules.system.entity.SystemMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     * @return List<SystemMenu>
     */
    List<SystemMenu> queryListParentId(Long parentId);
}
