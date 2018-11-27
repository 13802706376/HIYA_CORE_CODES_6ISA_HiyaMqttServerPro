package com.mqtt.bean.remote;

import java.util.List;

import com.mqtt.bean.RemoteOrderType;
import com.mqtt.util.json.gson.GjsonUtil;

/**
 * 系统升级命令
 * @author binggu
 *
 */
public class SystemUpgradeOrder  extends OrderCommon
{
    
    
    /**
     * 升级版本号
     */
    private String versionCode;  
    /**
     * 升级版本名称
     */
    private String versionName;
    /**
     * rom下载地址
     */
    private String url;   
    /**
     * MD5校验
     */
    private String md5;
    
    
    /**
     * 支持版本
     */
    private List<SupportVersion> supportVersionList;

    public String getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getUrl() {
        return url;
    }

    public String getMd5() {
        return md5;
    }

    public List<SupportVersion> getSupportVersionList() {
        return supportVersionList;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setSupportVersionList(List<SupportVersion> supportVersionList) {
        this.supportVersionList = supportVersionList;
    }
    
    public static void main(String[] args)
    {
        SystemUpgradeOrder order =new SystemUpgradeOrder();
        order.setMsgCode(RemoteOrderType.UPGRADE_ROM);
        order.setMd5("fsadfsd");
        order.setPlanId("1212");
        order.setRequestId(1212);
        System.out.println(GjsonUtil.toJson(order));
        
    }
}
