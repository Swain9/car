package cn.yuntangnet.duizhang.modules.system.shiro;

import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import cn.yuntangnet.duizhang.modules.system.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author 茂林
 * @since 2017/11/29 15:58
 */
@Component
public class AuthRealm extends AuthorizingRealm {
    private final ShiroService shiroService;

    @Autowired
    public AuthRealm(ShiroService shiroService) {
        this.shiroService = shiroService;
    }

    @Autowired
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 授权
     *
     * @param principal
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SystemUser entity = (SystemUser) principal.getPrimaryPrincipal();
        Long userId = entity.getUserId();
        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证
     *
     * @param token 用户输入的凭证
     * @return 认证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        //查询用户信息
        SystemUser user = shiroService.queryUser(username);
        if (user == null) {
            throw new UnknownAccountException("账号或密码错误");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }


}
