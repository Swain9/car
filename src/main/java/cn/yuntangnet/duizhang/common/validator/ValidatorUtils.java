package cn.yuntangnet.duizhang.common.validator;

import cn.yuntangnet.duizhang.exception.RtException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 参数校验工具类
 *
 * @author 茂林
 * @since 2017/11/30 16:18
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws RtException 校验不通过，则报RtException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws RtException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new RtException(msg.toString());
        }
    }
}
