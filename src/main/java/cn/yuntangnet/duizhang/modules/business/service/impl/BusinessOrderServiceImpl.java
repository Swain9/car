package cn.yuntangnet.duizhang.modules.business.service.impl;

import cn.yuntangnet.duizhang.common.util.PageInfo;
import cn.yuntangnet.duizhang.modules.business.entity.BusinessOrder;
import cn.yuntangnet.duizhang.modules.business.mapper.BusinessOrderMapper;
import cn.yuntangnet.duizhang.modules.business.service.IBusinessContentService;
import cn.yuntangnet.duizhang.modules.business.service.IBusinessOrderService;
import cn.yuntangnet.duizhang.modules.users.service.IUserWechatService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 茂林
 * @since 2018-02-28
 */
@Service
public class BusinessOrderServiceImpl extends ServiceImpl<BusinessOrderMapper, BusinessOrder> implements IBusinessOrderService {

}
