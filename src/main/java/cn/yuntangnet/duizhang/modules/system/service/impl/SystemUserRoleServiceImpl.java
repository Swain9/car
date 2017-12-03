package cn.yuntangnet.duizhang.modules.system.service.impl;

import cn.yuntangnet.duizhang.modules.system.entity.SystemUserRole;
import cn.yuntangnet.duizhang.modules.system.mapper.SystemUserRoleMapper;
import cn.yuntangnet.duizhang.modules.system.service.ISystemUserRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@Service
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> implements ISystemUserRoleService {

    /**
     * 更新用户与角色关系
     *
     * @param userId
     * @param roleIdList
     */
    @Override
    @Transactional
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if (roleIdList.size() == 0) {
            return;
        }

        //先删除用户与角色关系
        //delete from sys_user_role where user_id = #{value}
        EntityWrapper<SystemUserRole> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        delete(wrapper);

        //保存用户与角色关系
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);
        baseMapper.save(map);
    }
}
