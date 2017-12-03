package cn.yuntangnet.duizhang.modules.system.entity;

import java.io.Serializable;

import cn.yuntangnet.duizhang.common.validator.group.AddGroup;
import cn.yuntangnet.duizhang.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author 茂林
 * @since 2017-11-22
 */
@TableName("system_user")
public class SystemUser extends Model<SystemUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="user_id", type= IdType.AUTO)
	private Long userId;
    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String username;
    /**
     * 密码
     */
    @NotBlank(message="密码不能为空", groups = AddGroup.class)
	private String password;
    /**
     * 盐
     */
	private String salt;
    /**
     * 邮箱
     */
    @NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
	private Integer status;
    /**
     * 创建者ID
     */
	@TableField("create_user_id")
	private Long createUserId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


    /**
     * 角色ID列表
     */
    @TableField(exist = false)
    private List<Long> roleIdList;

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "SystemUser{" +
			", userId=" + userId +
			", username=" + username +
			", password=" + password +
			", salt=" + salt +
			", email=" + email +
			", mobile=" + mobile +
			", status=" + status +
			", createUserId=" + createUserId +
			", createTime=" + createTime +
			"}";
	}
}
