package com.bing.lan.share.ShareSDKUtil.bean;

import com.google.gson.Gson;

/**
 * Created by 蓝兵 on 2017/9/26.
 */

public class WechatBean {

    /**
     * city :
     * country :
     * expiresIn : 7200
     * expiresTime : 1506407038304
     * gender : 2
     * icon : http://wx.qlogo.cn/mmopen/vi_32/ibb1qx0mJOFAtl1CeibLxicW9DqsHUXTibfrUI2zMQuLnbvtANN8pbm5qkavr7VRlXlY48UHFCSDMD3TlnM04LoxGA/0
     * nickname : Ziv
     * openid : oVGJ_090cjHJFMKOiT6hKu1WZz6Y
     * province :
     * refresh_token : BFM2Q8Vl3QjXKhc43xdVL22Y4aN18xx92H-TZnGvL_lUIH2AiZfe9p9vrBVRgfwkSB4VOHNU_VC1jRr3-aFqNA
     * token : AxNRJAUH5zD8XxAWqj-5SN2hMXTPPPPBswcs96iGjhehHqig9EAtj1MRbuDC3rP3NvGhAwd2abCZxZ9IBevNFg
     * unionid : ofc4YwXFL0_8N1lRNKfWiFSCV1bY
     * userID : oVGJ_090cjHJFMKOiT6hKu1WZz6Y
     */

    private String city;
    private String country;
    private int expiresIn;
    private long expiresTime;
    private String gender;
    private String icon;
    private String nickname;
    private String openid;
    private String province;
    private String refresh_token;
    private String token;
    private String unionid;
    private String userID;

    public static WechatBean objectFromData(String str) {

        return new Gson().fromJson(str, WechatBean.class);
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "WechatBean{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", expiresIn=" + expiresIn +
                ", expiresTime=" + expiresTime +
                ", gender='" + gender + '\'' +
                ", icon='" + icon + '\'' +
                ", nickname='" + nickname + '\'' +
                ", openid='" + openid + '\'' +
                ", province='" + province + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", token='" + token + '\'' +
                ", unionid='" + unionid + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
