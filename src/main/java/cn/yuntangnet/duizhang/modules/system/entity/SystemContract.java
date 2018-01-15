package cn.yuntangnet.duizhang.modules.system.entity;

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
 * @since 2018-01-13
 */
@TableName("system_contract")
public class SystemContract extends Model<SystemContract> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 订单编号
     */
	@TableField("order_id")
	private String orderId;
    /**
     * 用户姓名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 手机号码
     */
	@TableField("user_phone")
	private String userPhone;
    /**
     * 所属代理商
     */
	@TableField("agent_name")
	private String agentName;
    /**
     * 合同信息
     */
	@TableField("contract_msg")
	private String contractMsg;
    /**
     * 附件1
     */
	private String annex1;
    /**
     * 提交人
     */
	@TableField("send_id")
	private Long sendId;
    /**
     * 提交人微信昵称
     */
	@TableField("send_name")
	private String sendName;
    /**
     * 附件2
     */
	private String annex2;
    /**
     * 合同状态:1.未提交,2.已提交,3.已审核
     */
	@TableField("contract_status")
	private String contractStatus;
    /**
     * 审核的管理员
     */
	@TableField("admin_name")
	private String adminName;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getContractMsg() {
		return contractMsg;
	}

	public void setContractMsg(String contractMsg) {
		this.contractMsg = contractMsg;
	}

	public String getAnnex1() {
		return annex1;
	}

	public void setAnnex1(String annex1) {
		this.annex1 = annex1;
	}

	public Long getSendId() {
		return sendId;
	}

	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getAnnex2() {
		return annex2;
	}

	public void setAnnex2(String annex2) {
		this.annex2 = annex2;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
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
		return "SystemContract{" +
			", id=" + id +
			", orderId=" + orderId +
			", userName=" + userName +
			", userPhone=" + userPhone +
			", agentName=" + agentName +
			", contractMsg=" + contractMsg +
			", annex1=" + annex1 +
			", sendId=" + sendId +
			", sendName=" + sendName +
			", annex2=" + annex2 +
			", contractStatus=" + contractStatus +
			", adminName=" + adminName +
			", gmtCreate=" + gmtCreate +
			", gmtModified=" + gmtModified +
			"}";
	}
}
