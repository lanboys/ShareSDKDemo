package com.bing.lan.share.ShareSDKUtil.bean;

import com.google.gson.Gson;

/**
 * Created by 蓝兵 on 2017/9/25.
 */

public class QQBean {

    /**
     * expiresIn : 7776000
     * expiresTime : 1506332401313
     * gender : 0
     * icon : http://q.qlogo.cn/qqapp/1106207632/80E557D66BC8B6530A9200C631DF7DB8/100
     * iconQzone : http://qzapp.qlogo.cn/qzapp/1106207632/80E557D66BC8B6530A9200C631DF7DB8/100
     * nickname : 新QQ号
     * pay_token : 606C3012C788C28634C2D2E5009366BE
     * pf : desktop_m_qq-10000144-android-2002-
     * pfkey : 4e8cc034820866439643d5aa181d6c44
     * secret :
     * secretType : 0
     * token : E29C917C573B89FFBE42A0203E9C3DCB
     * userID : 80E557D66BC8B6530A9200C631DF7DB8
     */

    private int expiresIn;
    private long expiresTime;
    private String gender;
    private String icon;
    private String iconQzone;
    private String nickname;
    private String pay_token;
    private String pf;
    private String pfkey;
    private String secret;
    private String secretType;
    private String token;
    private String userID;

    public static QQBean objectFromData(String str) {

        return new Gson().fromJson(str, QQBean.class);
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconQzone() {
        return iconQzone;
    }

    public void setIconQzone(String iconQzone) {
        this.iconQzone = iconQzone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPay_token() {
        return pay_token;
    }

    public void setPay_token(String pay_token) {
        this.pay_token = pay_token;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPfkey() {
        return pfkey;
    }

    public void setPfkey(String pfkey) {
        this.pfkey = pfkey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecretType() {
        return secretType;
    }

    public void setSecretType(String secretType) {
        this.secretType = secretType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "QQBean{" +
                "expiresIn=" + expiresIn +
                ", expiresTime=" + expiresTime +
                ", gender='" + gender + '\'' +
                ", icon='" + icon + '\'' +
                ", iconQzone='" + iconQzone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pay_token='" + pay_token + '\'' +
                ", pf='" + pf + '\'' +
                ", pfkey='" + pfkey + '\'' +
                ", secret='" + secret + '\'' +
                ", secretType='" + secretType + '\'' +
                ", token='" + token + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
