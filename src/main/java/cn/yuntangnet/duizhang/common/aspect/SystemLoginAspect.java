package cn.yuntangnet.duizhang.common.aspect;

import cn.yuntangnet.duizhang.common.annotation.SystemLoginAnnotation;
import cn.yuntangnet.duizhang.common.util.HttpContextUtils;
import cn.yuntangnet.duizhang.common.util.IPUtils;
import cn.yuntangnet.duizhang.common.util.JsonUtils;
import cn.yuntangnet.duizhang.common.util.ResultBean;
import cn.yuntangnet.duizhang.modules.system.entity.SystemLog;
import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import cn.yuntangnet.duizhang.modules.system.service.ISystemLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author 张茂林
 * @since 2018/1/5 14:36
 */
@Aspect
@Component
public class SystemLoginAspect {

    @Autowired
    private ISystemLogService systemLogService;

    @Autowired
    public SystemLoginAspect(ISystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    @Pointcut(value = "@annotation(cn.yuntangnet.duizhang.common.annotation.SystemLoginAnnotation)")
    public void systemLoginAspect() {

    }

    @AfterReturning(value = "systemLoginAspect() && @annotation(test)", returning = "bean")
    public void saveLoginLog(JoinPoint point, ResultBean bean, SystemLoginAnnotation test) {
        if (bean.getCode() != 200) {
            return;
        }
        //执行时长(毫秒)
        long time = 0L;
        //保存日志
        SystemLog systemLog = new SystemLog();
        //用户名
        String username = ((SystemUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
        systemLog.setUsername(username);
        //用户操作
        systemLog.setOperation(test.value());
        //请求的方法名
        String className = point.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        String methodName = signature.getName();
        systemLog.setMethod(className + "." + methodName + "()");
        //请求的参数
        Object[] args = point.getArgs();
        //todo 有多个参数,需要处理
        String params = JsonUtils.objectToJson(args[0]);
        systemLog.setParams(params);
        //执行时长
        systemLog.setTime(time);
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        systemLog.setIp(IPUtils.getIpAddr(request));
        systemLog.setCreateDate(new Date());
        //保存日志
        systemLogService.insert(systemLog);
    }
}
