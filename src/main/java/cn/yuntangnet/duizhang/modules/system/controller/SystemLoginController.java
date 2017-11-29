package cn.yuntangnet.duizhang.modules.system.controller;

import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.common.util.ShiroUtils;
import cn.yuntangnet.duizhang.modules.system.service.ISystemUserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 系统用户登陆控制器
 *
 * @author 茂林
 * @since 2017/11/28 17:36
 */
@RestController
public class SystemLoginController extends AbstractController {

    private final ISystemUserService systemUserService;
    private final Producer producer;

    @Autowired
    public SystemLoginController(ISystemUserService systemUserService, Producer producer) {
        this.systemUserService = systemUserService;
        this.producer = producer;
    }

    /**
     * 登陆
     *
     * @return
     */
    @PostMapping("/system/login")
    public ResultBean login(String username, String password, String captcha) {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultBean.error("验证码错误");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        ShiroUtils.login(token);

        return ResultBean.ok();
    }

    @PostMapping("/system/logout")
    public ResultBean logout() {
        ShiroUtils.logout();
        return ResultBean.ok();
    }

    /**
     * 验证码
     */
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }
}
