package cn.yuntangnet.duizhang.modules.system.controller;


import cn.yuntangnet.duizhang.common.util.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/system/user")
public class SystemUserController extends AbstractController {

    /**
     * 获取登录的用户信息
     *
     * @return
     */
    @RequestMapping("/info")
    public ResultBean info() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", getUser());
        return ResultBean.ok(map);
    }
}

