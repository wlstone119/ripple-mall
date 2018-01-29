package com.stone.ripple.dal.pojo.zhima;

public class AppZhimaManageDo {
    private Integer id;

    private String appKey;

    private String appId;

    private String privateZhimaKey;

    private String publicZhimaKey;

    private String env;

    private String appName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateZhimaKey() {
        return privateZhimaKey;
    }

    public void setPrivateZhimaKey(String privateZhimaKey) {
        this.privateZhimaKey = privateZhimaKey;
    }

    public String getPublicZhimaKey() {
        return publicZhimaKey;
    }

    public void setPublicZhimaKey(String publicZhimaKey) {
        this.publicZhimaKey = publicZhimaKey;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}