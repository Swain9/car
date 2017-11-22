package cn.yuntangnet.duizhang.modules.system.service.impl;

import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import cn.yuntangnet.duizhang.modules.system.mapper.SystemUserMapper;
import cn.yuntangnet.duizhang.modules.system.service.ISystemUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

}
