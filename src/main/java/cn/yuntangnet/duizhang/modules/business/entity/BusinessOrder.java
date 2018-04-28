package cn.yuntangnet.duizhang.modules.business.entity;

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
 * @since 2018-02-28
 */
@TableName("business_order")
public class BusinessOrder extends Model<BusinessOrder> {

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
     * 活动ID
     */
	@TableField("content_id")
	private Long contentId;
    /**
     * 用户姓名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 联系手机号码
     */
	@TableField("user_phone")
	private String userPhone;
    /**
     * 支付金额
     */
	@TableField("pay_money")
	private Long payMoney;
    /**
     * 支付日期
     */
	@TableField("pay_datetime")
	private Date payDatetime;
    /**
     * 支付方式 1.微信支付 2.支付宝支付 3.银联支付,4.后台添加
     */
	@TableField("pay_type")
	private String payType;
    /**
     * 是否已付款
     */
	@TableField("is_pay")
	private Integer isPay;
    /**
     * 是否已处理
     */
	@TableField("is_done")
	private Integer isDone;
    /**
     * 返积分账号
     */
	@TableField("platform_id")
	private String platformId;
    /**
     * 返积分手机号码
     */
	@TableField("platform_phone")
	private String platformPhone;
    /**
     * 返积分平台
     */
	@TableField("platform_name")
	private String platformName;
    /**
     * 操作的管理员
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

	public Long getUserWechatId() {
		return userWechatId;
	}

	public void setUserWechatId(Long userWechatId) {
		this.userWechatId = userWechatId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
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

	public Long getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Long payMoney) {
		this.payMoney = payMoney;
	}

	public Date getPayDatetime() {
		return payDatetime;
	}

	public void setPayDatetime(Date payDatetime) {
		this.payDatetime = payDatetime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Integer getIsDone() {
		return isDone;
	}

	public void setIsDone(Integer isDone) {
		this.isDone = isDone;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getPlatformPhone() {
		return platformPhone;
	}

	public void setPlatformPhone(String platformPhone) {
		this.platformPhone = platformPhone;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
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
		return "BusinessOrder{" +
			", id=" + id +
			", userWechatId=" + userWechatId +
			", contentId=" + contentId +
			", userName=" + userName +
			", userPhone=" + userPhone +
			", payMoney=" + payMoney +
			", payDatetime=" + payDatetime +
			", payType=" + payType +
			", isPay=" + isPay +
			", isDone=" + isDone +
			", platformId=" + platformId +
			", platformPhone=" + platformPhone +
			", platformName=" + platformName +
			", adminName=" + adminName +
			", gmtCreate=" + gmtCreate +
			", gmtModified=" + gmtModified +
			"}";
	}
}
