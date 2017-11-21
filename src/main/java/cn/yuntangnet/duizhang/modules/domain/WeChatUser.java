package cn.yuntangnet.duizhang.modules.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by 45022 on 2017/7/28.
 */
@Entity
public class WeChatUser {
    //1	    user_id	bigint			用户主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //2	    subscribe	varchar	1		为0表示未关注公众号
    private String subscribe;
    //3	    openid	varchar	32		公众号唯一ID
    private String openid;
    //4	    unionid	varchar	32		开放平台唯一ID
    private String unionid;
    //5	    nickname	varchar	50		昵称
    private String nickname;
    //6	    sex	varchar	1		1.男性 2.女性 0.未知
    private String sex;
    //7	    city	varchar	50		城市
    private String city;
    //8	    country	varchar	50		国家
    private String country;
    //9	    province	varchar	50		省份
    private String province;
    //10	languages	varchar	20		语言
    private String languages;
    //11	headimgurl	varchar	200		头像
    private String headimgurl;
    //12	subscribe_time	datetime			用户关注时间
    private String subscribe_time;
    //13	remark	varchar	50		备注
    private String remark;
    //14	groupid	varchar	50		分组ID
    private String groupid;
    //15	tagid_list	varchar	50		用户被打上的标签ID列表
    private String tagid_list;
    //16	is_deleted	tinyint			是否已删除1.已删除,0未删除
    private boolean is_deleted;
    //17	gmt_create	datetime			创建日期
    private Date gmt_create;
    //18	gmt_modified	datetime			更新日期
    private Date gmt_modified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
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

    public String getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(String tagid_list) {
        this.tagid_list = tagid_list;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }
}
