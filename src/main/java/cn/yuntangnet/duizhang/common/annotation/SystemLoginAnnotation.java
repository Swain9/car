package cn.yuntangnet.duizhang.common.annotation;

import java.lang.annotation.*;

/**
 * @author 张茂林
 * @since 2018/1/5 14:35
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLoginAnnotation {
    String value() default "";
}
