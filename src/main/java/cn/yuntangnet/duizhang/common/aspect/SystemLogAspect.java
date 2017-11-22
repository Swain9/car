package cn.yuntangnet.duizhang.common.aspect;

import cn.yuntangnet.duizhang.common.annotation.SystemLogAnnotation;
import cn.yuntangnet.duizhang.common.util.HttpContextUtils;
import cn.yuntangnet.duizhang.common.util.IPUtils;
import cn.yuntangnet.duizhang.common.util.JsonUtils;
import cn.yuntangnet.duizhang.modules.system.entity.SystemLog;
import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import cn.yuntangnet.duizhang.modules.system.service.ISystemLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author 茂林
 * @since 2017/11/21 13:38
 */
@Aspect
@Component
public class SystemLogAspect {

    private final ISystemLogService systemLogService;

    @Autowired
    public SystemLogAspect(ISystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    @Pointcut(value = "@annotation(cn.yuntangnet.duizhang.common.annotation.SystemLogAnnotation)")
    public void systemLogAspect() {

    }

    @Around(value = "systemLogAspect() && @annotation(systemLogAnnotation)")
    public Object saveSystemLog(ProceedingJoinPoint point, SystemLogAnnotation systemLogAnnotation) throws Throwable {
        //当前系统个时间
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        SystemLog systemLog = new SystemLog();
        //用户名
        String username = ((SystemUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
        systemLog.setUsername(username);
        //用户操作
        systemLog.setOperation(systemLogAnnotation.value());
        //请求的方法名
        String className = point.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        String methodName = signature.getName();
        systemLog.setMethod(className + "." + methodName + "()");
        //请求的参数
        Object[] args = point.getArgs();
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
        //返回结果
        return result;
    }
}
