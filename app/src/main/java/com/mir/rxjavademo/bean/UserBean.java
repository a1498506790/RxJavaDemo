package com.mir.rxjavademo.bean;

public class UserBean {


    /**
     * id : 用户ID
     * nickname : 昵称
     * head : 头像
     * mobile : 手机号
     * paypassword : 当为空时代码没有设置支付密码，当有值时则已设置
     * level : VIP级别名称a
     * prestige : 威望值
     * cashbalance : 可用余额
     * fundbalance : 基金余额
     * sign : 签到状态：0-未签到，1-已签到
     * createtime : 注册时间
     */

    private String id;
    private String nickname;
    private String head;
    private String mobile;
    private String paypassword;
    private String level;
    private String prestige;
    private double cashbalance;
    private double fundbalance;
    private String sign;
    private String createtime;
    private double present;
    private double present2;
    private double activitypresent;
    private String recode;

    public String getRecode() {
        return recode;
    }

    public void setRecode(String recode) {
        this.recode = recode;
    }

    public double getPresent() {
        return present;
    }

    public void setPresent(double present) {
        this.present = present;
    }

    public double getPresent2() {
        return present2;
    }

    public void setPresent2(double present2) {
        this.present2 = present2;
    }

    public double getActivitypresent() {
        return activitypresent;
    }

    public void setActivitypresent(double activitypresent) {
        this.activitypresent = activitypresent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPrestige() {
        return prestige;
    }

    public void setPrestige(String prestige) {
        this.prestige = prestige;
    }

    public double getCashbalance() {
        return cashbalance;
    }

    public void setCashbalance(double cashbalance) {
        this.cashbalance = cashbalance;
    }

    public double getFundbalance() {
        return fundbalance;
    }

    public void setFundbalance(double fundbalance) {
        this.fundbalance = fundbalance;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", head='" + head + '\'' +
                ", mobile='" + mobile + '\'' +
                ", paypassword='" + paypassword + '\'' +
                ", level='" + level + '\'' +
                ", prestige='" + prestige + '\'' +
                ", cashbalance='" + cashbalance + '\'' +
                ", fundbalance='" + fundbalance + '\'' +
                ", sign='" + sign + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
