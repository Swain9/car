package cn.yuntangnet.duizhang.modules.app.controller;


import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.common.validator.Assert;
import cn.yuntangnet.duizhang.modules.app.utils.JwtUtils;
import cn.yuntangnet.duizhang.modules.users.service.IUserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * APP登录授权
 */
@RestController
@RequestMapping("/app")
public class ApiLoginController {
    @Autowired
    private IUserBaseService userBaseService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录
     */
    @PostMapping("login")
    public ResultBean login(String loginid, String password) {
        Assert.isBlank(loginid, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userBaseService.login(loginid, password);

        //生成token
        String token = jwtUtils.generateToken(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());

        return ResultBean.ok(map);
    }

}
