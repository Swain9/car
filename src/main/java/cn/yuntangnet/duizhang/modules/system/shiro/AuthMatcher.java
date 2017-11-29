package cn.yuntangnet.duizhang.modules.system.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Component;

/**
 * @author 茂林
 * @since 2017/11/29 19:02
 */
@Component
public class AuthMatcher extends SimpleCredentialsMatcher {

    //密码比较的方法   token代表用户在界面输入的用户名和密码     info代表从数据库中得到加密数据
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //1.向下转型
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        SimpleAuthenticationInfo upInfo = (SimpleAuthenticationInfo)info;
        //2.将用户在界面输入的原始密码加密

        String s = new Sha256Hash(upToken.getPassword(), upInfo.getCredentialsSalt().getBytes()).toHex();

        //3.取出数据库中加密的密码
        Object dbPwd = info.getCredentials();

        return this.equals(s, dbPwd);
    }
}
