package cn.yuntangnet.duizhang.modules.business.service;

import com.catt.ipnet.confsrv.common.bo.RestResponse;
import com.catt.ipnet.confsrv.common.bo.gxcloudpool.GxCloudPoolResult;

/**
 * <PRE>
 * 这里填写类注释
 * </PRE>
 *
 * @author zhangmaolin
 * @version 1.0.0
 * @since 2018-12-19 14:09
 */
public interface GuangXiBusiness {

    RestResponse<GxCloudPoolResult> createGuangXiCloudPoolZhuanXian();

    RestResponse<GxCloudPoolResult> deleteGuangXiCloudPoolZhuanXian();

}
