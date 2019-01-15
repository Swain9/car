package cn.yuntangnet.duizhang.HutoolTest;

import cn.hutool.core.util.ClassUtil;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * <PRE>
 * 这里填写类注释
 * </PRE>
 *
 * @author zhangmaolin
 * @version 1.0.0
 * @since 2018-12-21 16:23
 */
public class HutoolTest {

    @Test
    public void test(){
        Method forType = ClassUtil.getDeclaredMethod(ParameterizedTypeReference.class, "forType", Type.class);
        System.out.println(forType);
    }
}
