package com.mqtt.bean.remote;

import java.util.List;

/**
 * app卸载命令
 * @author BingGu
 *
 */
public class AppUninstallOrder extends OrderCommon
{
    
    
    private List<APPInfo> appInfoList;
    
    
    public List<APPInfo> getAppInfoList()
    {
        return appInfoList;
    }
    
    public void setAppInfoList(List<APPInfo> appInfoList)
    {
        this.appInfoList = appInfoList;
    }
    
    /**
     * 要卸载的APP信息封装类
     * @author Roy
     *
     */
    public static class APPInfo
    {
        
        /**
         * 应用名
         */
        private String appName;
        
        /**
         * 包名
         */
        private String packageName;
        
        
        public String getAppName()
        {
            return appName;
        }
        
        public String getPackageName()
        {
            return packageName;
        }
        
        public void setAppName(String appName)
        {
            this.appName = appName;
        }
        
        public void setPackageName(String packageName)
        {
            this.packageName = packageName;
        }

        
    }
}
