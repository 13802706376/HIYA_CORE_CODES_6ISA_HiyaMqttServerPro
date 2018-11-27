package com.mqtt.bean.forStb;

public class APPInfo
{

    /**
     * packageName
     */
    private String packageName;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * app版本
     */
    private Integer versionCode; 
    /**
     * APP版本名称
     */
    private String versionName;
    /**
     * 是否第三方应用   
     * 0:false
     * 1:true
     */
    private Integer isThirdApp;
    
    
    
    
    public String getPackageName()
    {
        return packageName;
    }
    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }
    public String getAppName()
    {
        return appName;
    }
    public void setAppName(String appName)
    {
        this.appName = appName;
    }
    public Integer getVersionCode()
    {
        return versionCode;
    }
    public void setVersionCode(Integer versionCode)
    {
        this.versionCode = versionCode;
    }
    public String getVersionName()
    {
        return versionName;
    }
    public void setVersionName(String versionName)
    {
        this.versionName = versionName;
    }
    public Integer getIsThirdApp()
    {
        return isThirdApp;
    }
    public void setIsThirdApp(Integer isThirdApp)
    {
        this.isThirdApp = isThirdApp;
    }

    
}
