package com.mqtt.bean.forStb;

import java.util.List;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

/**
 * 设备状态信息
 * @author Roy
 * 上报时机：每个小时上报一次，包括内存、cpu使用情况，还有App使用情况
 */


public class DeviceStatusInfo extends AbsReportMsg {

    /**
     * CPU使用率
     */
    private String cpuUseBit;  //CPU使用率
    /**
     * 总内存大小
     */
    private long tatalMemory; //总内存大小（KB）
    /**
     * 剩余内存大小
     */
    private  long remainMemory; // 剩余内存大小 （KB）
    
    private List<APPInfo> appInfos;
    
    public String getCpuUseBit() {
        return cpuUseBit;
    }

    public void setCpuUseBit(String cpuUseBit) {
        this.cpuUseBit = cpuUseBit;
    }

    public long getTatalMemory() {
        return tatalMemory;
    }

    public void setTatalMemory(long tatalMemory) {
        this.tatalMemory = tatalMemory;
    }

    public long getRemainMemory() {
        return remainMemory;
    }

    public void setRemainMemory(long remainMemory) {
        this.remainMemory = remainMemory;
    }

    public List<APPInfo> getAppInfos() {
        return appInfos;
    }

    public void setAppInfos(List<APPInfo> appInfos) {
        this.appInfos = appInfos;
    }

    /**
     * app信息
     * @author Roy
     *
     */
    public static class APPInfo{
        
        /**
         * 包名
         */
        private String packageName;   //pacakge
        
        /**
         * app版本
         */
        private String versionCode;   //APP版本
        /**
         * app的cup使用率
         */
        private String cpuUserBit;   //appCPU使用率
        /**
         * app使用内存大小
         */
        private long useMemory;  //APP使用内存大小（KB）
        /**
         * app运行时长
         */
        private long runTime;  //APP运行时长（min）
        
        
        public String getPackageName() {
            return packageName;
        }
        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }
        public String getVersionCode() {
            return versionCode;
        }
        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }
        public String getCpuUserBit() {
            return cpuUserBit;
        }
        public void setCpuUserBit(String cpuUserBit) {
            this.cpuUserBit = cpuUserBit;
        }
        public long getUseMemory() {
            return useMemory;
        }
        public void setUseMemory(long useMemory) {
            this.useMemory = useMemory;
        }
        public long getRunTime() {
            return runTime;
        }
        public void setRunTime(long runTime) {
            this.runTime = runTime;
        }
        
    }

    /**
     * 拼写规则：
     * msgCode||msgExplain||occurTime||deviceMac||
     * cpuUseBit||tatalMemory||remainMemory||
     * packageName,versionCode,cpuUserBit,useMemory,runTime;
     * packageName,versionCode,cpuUserBit,useMemory,runTime ......
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(cpuUseBit).append(Constants.HADOOP_COMMAS).append(tatalMemory).append(Constants.HADOOP_COMMAS).append(remainMemory).append(Constants.HADOOP_COMMAS);
        if(appInfos==null||appInfos.size()<1)
        {
            bu.append("null");  
        }else
        {
            for(APPInfo app:appInfos)
            {
                bu.append(app.getPackageName()).append(",").append(app.getVersionCode()).append(",").append(app.getCpuUserBit()).append(",").append(app.getUseMemory()).append(",").append(app.getRunTime()).append(";");
            }
        }
        if(bu.length()>0)
        	bu.deleteCharAt(bu.length()-1);
        String result=bu.toString();
        bu = null;
        return result;
    }
    
}
