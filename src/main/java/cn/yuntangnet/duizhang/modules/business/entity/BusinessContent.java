package cn.yuntangnet.duizhang.modules.business.entity;

import cn.yuntangnet.duizhang.common.validator.group.AddGroup;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author 茂林
 * @since 2018-02-26
 */
@TableName("business_content")
public class BusinessContent extends Model<BusinessContent> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 主题
     */
    @TableField("content_theme")
    @NotBlank(message = "主题不能为空", groups = AddGroup.class)
    private String contentTheme;
    /**
     * 讲师
     */
    @TableField("content_teacher")
    @NotBlank(message = "讲师不能为空", groups = AddGroup.class)
    private String contentTeacher;
    /**
     * 活动费用
     */
    @TableField("content_money")
    @Min(value = 0, message = "金额必须大于0", groups = AddGroup.class)
    private Long contentMoney;
    /**
     * 活动说明
     */
    @TableField("content_details")
    private String contentDetails;
    /**
     * 付款用户
     */
    @TableField("content_paying_count")
    private Integer contentPayingCount;
    /**
     * 到场人数
     */
    @TableField("content_signin_count")
    private Integer contentSigninCount;
    /**
     * 统计已缴费用
     */
    @TableField("content_total_money")
    private Long contentTotalMoney;
    /**
     * 活动状态:1.已开启,0.未开启 2.已结束,3已关闭
     */
    @TableField("content_status")
    private String contentStatus;
    /**
     * 开始日期
     */
    @TableField("begin_time")
    private Date beginTime;
    /**
     * 结束日期
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 操作的管理员
     */
    @TableField("admin_name")
    private String adminName;
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

    public String getContentTheme() {
        return contentTheme;
    }

    public void setContentTheme(String contentTheme) {
        this.contentTheme = contentTheme;
    }

    public String getContentTeacher() {
        return contentTeacher;
    }

    public void setContentTeacher(String contentTeacher) {
        this.contentTeacher = contentTeacher;
    }

    public Long getContentMoney() {
        return contentMoney;
    }

    public void setContentMoney(Long contentMoney) {
        this.contentMoney = contentMoney;
    }

    public String getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(String contentDetails) {
        this.contentDetails = contentDetails;
    }

    public Integer getContentPayingCount() {
        return contentPayingCount;
    }

    public void setContentPayingCount(Integer contentPayingCount) {
        this.contentPayingCount = contentPayingCount;
    }

    public Integer getContentSigninCount() {
        return contentSigninCount;
    }

    public void setContentSigninCount(Integer contentSigninCount) {
        this.contentSigninCount = contentSigninCount;
    }

    public Long getContentTotalMoney() {
        return contentTotalMoney;
    }

    public void setContentTotalMoney(Long contentTotalMoney) {
        this.contentTotalMoney = contentTotalMoney;
    }

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
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
        return "BusinessContent{" +
                ", id=" + id +
                ", contentTheme=" + contentTheme +
                ", contentTeacher=" + contentTeacher +
                ", contentMoney=" + contentMoney +
                ", contentDetails=" + contentDetails +
                ", contentPayingCount=" + contentPayingCount +
                ", contentSigninCount=" + contentSigninCount +
                ", contentTotalMoney=" + contentTotalMoney +
                ", contentStatus=" + contentStatus +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", adminName=" + adminName +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}";
    }
}
