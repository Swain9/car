package cn.yuntangnet.duizhang.modules.users.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 茂林
 * @since 2017-12-17
 */
@TableName("work_order")
public class WorkOrder extends Model<WorkOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 下单ID
     */
	@TableField("user_wechat_id")
	private Long userWechatId;
    /**
     * 工单类型
     */
	@TableField("order_type")
	private String orderType;
    /**
     * 代理商区域
     */
	@TableField("agent_area")
	private String agentArea;
    /**
     * 代理商姓名
     */
	@TableField("agent_name")
	private String agentName;
    /**
     * 联系手机
     */
	@TableField("user_phone")
	private String userPhone;
    /**
     * 订单状态:1未处理,2.已处理
     */
	@TableField("order_status")
	private String orderStatus;
    /**
     * 问题描述
     */
	private String question;
    /**
     * 处理意见
     */
	private String msg;
    /**
     * 附件
     */
	private String annex;
    /**
     * 创建日期
     */
	@TableField("gmt_create")
	private Date gmtCreate;
    /**
     * 更新日期
     */
	@TableField("gmt_modified")
	private Date gmtModified;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserWechatId() {
		return userWechatId;
	}

	public void setUserWechatId(Long userWechatId) {
		this.userWechatId = userWechatId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getAgentArea() {
		return agentArea;
	}

	public void setAgentArea(String agentArea) {
		this.agentArea = agentArea;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAnnex() {
		return annex;
	}

	public void setAnnex(String annex) {
		this.annex = annex;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WorkOrder{" +
			", id=" + id +
			", userWechatId=" + userWechatId +
			", orderType=" + orderType +
			", agentArea=" + agentArea +
			", agentName=" + agentName +
			", userPhone=" + userPhone +
			", orderStatus=" + orderStatus +
			", question=" + question +
			", msg=" + msg +
			", annex=" + annex +
			", gmtCreate=" + gmtCreate +
			", gmtModified=" + gmtModified +
			"}";
	}
}
