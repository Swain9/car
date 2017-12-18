package cn.yuntangnet.duizhang.modules.app.controller;


import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.common.validator.Assert;
import cn.yuntangnet.duizhang.modules.users.service.IUserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册
 */
@RestController
@RequestMapping("/app")
public class ApiRegisterController {
    @Autowired
    private IUserBaseService userBaseService;

    /**
     * 注册
     */
    @PostMapping("register")
    public ResultBean register(String userName, String userPhone, String userPwd) {
        Assert.isBlank(userName, "用户名不能为空");
        Assert.isBlank(userPhone, "手机号不能为空");
        Assert.isBlank(userPwd, "密码不能为空");

        userBaseService.register(userName, userPhone, userPwd);

        return ResultBean.ok();
    }
}
