package cn.yuntangnet.duizhang.modules.users.service;

import cn.yuntangnet.duizhang.modules.users.entity.UserBase;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户列表 服务类
 * </p>
 *
 * @author 茂林
 * @since 2017-12-08
 */
public interface IUserBaseService extends IService<UserBase> {
    /**
     * 用户登陆
     *
     * @param loginid
     * @param password
     * @return
     */
    long login(String loginid, String password);

    /**
     * 根据用户名或者手机号码查询
     *
     * @param loginid
     * @return
     */
    UserBase queryByLogin(String loginid);

    /**
     * 用户注册
     *
     * @param userName
     * @param userPhone
     * @param userPwd
     */
    void register(String userName, String userPhone, String userPwd);
}
