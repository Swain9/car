package cn.yuntangnet.duizhang.modules.users.entity;

import cn.yuntangnet.duizhang.common.validator.group.AddGroup;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户列表
 * </p>
 *
 * @author 茂林
 * @since 2017-12-08
 */
@TableName("user_base")
public class UserBase extends Model<UserBase> {

    private static final long serialVersionUID = 1L;

    /**
     * ID主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 登录名
     */
    @TableField("user_name")
    @NotBlank(message = "用户名不能为空", groups = AddGroup.class)
    private String userName;
    /**
     * 手机号码
     */
    @TableField("user_phone")
    @NotBlank(message = "手机号码不能为空", groups = AddGroup.class)
    private String userPhone;
    /**
     * 登陆密码
     */
    @TableField("user_pwd")
    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    private String userPwd;
    /**
     * 安全密码
     */
    @TableField("user_spwd")
    private String userSpwd;
    /**
     * 邮箱
     */
    @TableField("user_email")
    private String userEmail;
    /**
     * 用户级别：1.普通用户，2代理商
     */
    @TableField("user_level")
    private String userLevel;
    /**
     * 注册来源：1.后台添加，2.微信注册，3小程序注册
     */
    @TableField("user_from")
    private String userFrom;
    /**
     * 推荐人
     */
    @TableField("user_parent_id")
    private Long userParentId;
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

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserSpwd() {
        return userSpwd;
    }

    public void setUserSpwd(String userSpwd) {
        this.userSpwd = userSpwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public Long getUserParentId() {
        return userParentId;
    }

    public void setUserParentId(Long userParentId) {
        this.userParentId = userParentId;
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
        return "UserBase{" +
                ", id=" + id +
                ", userName=" + userName +
                ", userPhone=" + userPhone +
                ", userPwd=" + userPwd +
                ", userSpwd=" + userSpwd +
                ", userEmail=" + userEmail +
                ", userLevel=" + userLevel +
                ", userFrom=" + userFrom +
                ", userParentId=" + userParentId +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}";
    }
}
