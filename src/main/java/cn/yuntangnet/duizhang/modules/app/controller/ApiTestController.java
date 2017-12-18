package cn.yuntangnet.duizhang.modules.app.controller;


import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.modules.app.annotation.Login;
import cn.yuntangnet.duizhang.modules.app.annotation.LoginUser;
import cn.yuntangnet.duizhang.modules.users.entity.UserBase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP测试接口
 */
@RestController
@RequestMapping("/app")
public class ApiTestController {

    /**
     * 获取用户信息
     */
    @Login
    @GetMapping("userInfo")
    public ResultBean userInfo(@LoginUser UserBase user) {
        return ResultBean.ok(user);
    }

    /**
     * 获取用户ID
     */
    @Login
    @GetMapping("userId")
    public ResultBean userInfo(@RequestAttribute("userId") Integer userId) {
        return ResultBean.ok(userId);
    }

    /**
     * 忽略Token验证测试
     */
    @GetMapping("notToken")
    public ResultBean notToken() {
        return ResultBean.ok("无需token也能访问。。。");
    }

}
