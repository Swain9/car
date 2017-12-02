package cn.yuntangnet.duizhang.modules.system.service.impl;

import cn.yuntangnet.duizhang.modules.system.entity.SystemRoleMenu;
import cn.yuntangnet.duizhang.modules.system.mapper.SystemRoleMenuMapper;
import cn.yuntangnet.duizhang.modules.system.service.ISystemRoleMenuService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@Service
public class SystemRoleMenuServiceImpl extends ServiceImpl<SystemRoleMenuMapper, SystemRoleMenu> implements ISystemRoleMenuService {

    /**
     * 更新或修改角色与菜单关联信息
     *
     * @param roleId     roleId
     * @param menuIdList menuIdList
     */
    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色与菜单关系
        //deleteById(roleId);
        EntityWrapper<SystemRoleMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id", roleId);
        delete(wrapper);

        if (menuIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        baseMapper.save(map);
    }
}
