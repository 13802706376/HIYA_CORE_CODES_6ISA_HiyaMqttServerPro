package com.mqtt.bean.remote;

import java.util.List;
/**
 * app升级回复消息
 * @author BingGu
 *
 */
public class AppUpgradeReply extends OrderCommon
{
    
    
    /**
     * 升级的app信息
     */
    private List<APPInfo> appInfoList;
        
    public List<APPInfo> getAppInfoList() {
        return appInfoList;
    }

    public void setAppInfoList(List<APPInfo> appInfoList) {
        this.appInfoList = appInfoList;
    }

    /**
     * 升级app信息封装类
     *
     */
    public static class APPInfo {

        /**
         * 升级是否成功 1：成功，0：失败
         */
        private int resultCode; 
        /**
         * 升级结果描述
         */
        private String resultMsg; 
        /**
         * 升级包名
         */
        private String packageName;
        /**
         * 当前版本
         */
        private String versionCode;
        
        public int getResultCode() {
            return resultCode;
        }
        public String getResultMsg() {
            return resultMsg;
        }
        public String getPackageName() {
            return packageName;
        }
        public String getVersionCode() {
            return versionCode;
        }
        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }
        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }
        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        } 

    }

}
