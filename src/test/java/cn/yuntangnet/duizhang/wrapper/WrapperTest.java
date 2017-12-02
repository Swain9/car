package cn.yuntangnet.duizhang.wrapper;

import cn.yuntangnet.duizhang.DuizhangApplicationTests;
import cn.yuntangnet.duizhang.modules.system.entity.SystemConfig;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.junit.Test;

/**
 * @author 茂林
 * @since 2017/11/30 18:25
 */
public class WrapperTest extends DuizhangApplicationTests{
    @Test
    public void testTSQL11() {
    /*
     * 实体带查询使用方法  输出看结果
     */
        EntityWrapper<SystemConfig> wrapper = new EntityWrapper<>();

            wrapper.where(" key LIKE {0}", "'123'");

        System.out.println(wrapper.getSqlSegment());
    }
}
