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
@TableName("system_work_order")
public class SystemWorkOrder extends Model<SystemWorkOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 发布工单的ID
     */
	@TableField("from_id")
	private Long fromId;
    /**
     * 需要完成工单的ID
     */
	@TableField("to_id")
	private Long toId;
    /**
     * 任务描述
     */
	private String question;
    /**
     * 完成描述
     */
	private String msg;
    /**
     * 附件
     */
	private String annex;
    /**
     * 1.已读,0.未读
     */
	private Integer readStatus;
    /**
     * 1.未处理,2.已处理,3.驳回,4.已完成
     */
	private String orderStatus;
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

	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	public Long getToId() {
		return toId;
	}

	public void setToId(Long toId) {
		this.toId = toId;
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

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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
		return "SystemWorkOrder{" +
			", id=" + id +
			", fromId=" + fromId +
			", toId=" + toId +
			", question=" + question +
			", msg=" + msg +
			", annex=" + annex +
			", readStatus=" + readStatus +
			", orderStatus=" + orderStatus +
			", gmtCreate=" + gmtCreate +
			", gmtModified=" + gmtModified +
			"}";
	}
}
