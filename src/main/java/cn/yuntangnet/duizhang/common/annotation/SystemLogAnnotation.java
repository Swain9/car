package cn.yuntangnet.duizhang.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author 茂林
 * @since 2017/11/21 12:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLogAnnotation {
    String value() default "";
}
