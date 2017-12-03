package cn.yuntangnet.duizhang.common.validator;

import cn.yuntangnet.duizhang.exception.RtException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 茂林
 * @since 2017/12/3 11:40
 */
public class Assert {
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RtException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RtException(message);
        }
    }
}
