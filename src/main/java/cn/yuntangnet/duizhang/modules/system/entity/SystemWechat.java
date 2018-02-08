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
 * @since 2017-12-26
 */
@TableName("system_wechat")
public class SystemWechat extends Model<SystemWechat> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 为0表示未关注公众号
     */
	private String subscribe;
    /**
     * 公众号唯一ID
     */
	private String openid;
    /**
     * 开放平台唯一ID
     */
	private String unionid;
    /**
     * 昵称
     */
	private String nickname;
    /**
     * 1.男性，2.女性，0.未知
     */
	private String sex;
    /**
     * 城市
     */
	private String city;
    /**
     * 国家
     */
	private String country;
    /**
     * 省份
     */
	private String province;
    /**
     * 语言
     */
	private String languages;
    /**
     * 头像
     */
	private String headimagurl;
    /**
     * 用户关注时间
     */
	@TableField("subcribe_time")
	private Date subcribeTime;
    /**
     * 备注
     */
	private String remark;
    /**
     * 分组ID
     */
	private String groupid;
    /**
     * 用户被打上的标签ID列表
     */
	@TableField("tagid_list")
	private String tagidList;
    /**
     * 是否已删除：1.已删除，0.未删除
     */
	@TableField("is_deleted")
	private Integer isDeleted;
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

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getHeadimagurl() {
		return headimagurl;
	}

	public void setHeadimagurl(String headimagurl) {
		this.headimagurl = headimagurl;
	}

	public Date getSubcribeTime() {
		return subcribeTime;
	}

	public void setSubcribeTime(Date subcribeTime) {
		this.subcribeTime = subcribeTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getTagidList() {
		return tagidList;
	}

	public void setTagidList(String tagidList) {
		this.tagidList = tagidList;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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
		return "SystemWechat{" +
			", id=" + id +
			", subscribe=" + subscribe +
			", openid=" + openid +
			", unionid=" + unionid +
			", nickname=" + nickname +
			", sex=" + sex +
			", city=" + city +
			", country=" + country +
			", province=" + province +
			", languages=" + languages +
			", headimagurl=" + headimagurl +
			", subcribeTime=" + subcribeTime +
			", remark=" + remark +
			", groupid=" + groupid +
			", tagidList=" + tagidList +
			", isDeleted=" + isDeleted +
			", gmtCreate=" + gmtCreate +
			", gmtModified=" + gmtModified +
			"}";
	}
}
