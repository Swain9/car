package cn.yuntangnet.duizhang.modules.users.service.impl;

import cn.yuntangnet.duizhang.common.util.Constant;
import cn.yuntangnet.duizhang.common.validator.Assert;
import cn.yuntangnet.duizhang.exception.RtException;
import cn.yuntangnet.duizhang.modules.users.entity.UserBase;
import cn.yuntangnet.duizhang.modules.users.mapper.UserBaseMapper;
import cn.yuntangnet.duizhang.modules.users.service.IUserBaseService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户列表 服务实现类
 * </p>
 *
 * @author 茂林
 * @since 2017-12-08
 */
@Service
public class UserBaseServiceImpl extends ServiceImpl<UserBaseMapper, UserBase> implements IUserBaseService {

    /**
     * 用户登陆
     *
     * @param loginid
     * @param password
     * @return
     */
    @Override
    public long login(String loginid, String password) {
        UserBase user = queryByLogin(loginid);
        Assert.isNull(user, "账号或密码错误");

        //密码错误
        if (!user.getUserPwd().equals(DigestUtils.sha256Hex(password))) {
            throw new RtException("账号或密码错误");
        }

        return user.getId();
    }

    /**
     * 根据用户名或者手机号码查询
     *
     * @param loginid
     * @return
     */
    public UserBase queryByLogin(String loginid) {
        EntityWrapper<UserBase> wrapper = new EntityWrapper<>();
        wrapper.eq("user_name", loginid).or().eq("user_phone", loginid);
        List<UserBase> list = baseMapper.selectList(wrapper);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 用户注册
     *
     * @param userName
     * @param userPhone
     * @param userPwd
     */
    @Override
    @Transactional
    public void register(String userName, String userPhone, String userPwd) {
        UserBase userBase = new UserBase();
        userBase.setUserName(userName);
        userBase.setUserPhone(userPhone);
        userBase.setUserPwd(DigestUtils.sha256Hex(userPwd));
        userBase.setUserLevel(Constant.UserLevel.PUTONG.getValue());
        userBase.setUserFrom(Constant.UserFrom.WEIXIN.getValue());
        userBase.setIsDeleted(Constant.DataStatus.ENABLED.getValue());
        Date date = new Date();
        userBase.setGmtCreate(date);
        userBase.setGmtModified(date);
        baseMapper.insert(userBase);
    }
}
