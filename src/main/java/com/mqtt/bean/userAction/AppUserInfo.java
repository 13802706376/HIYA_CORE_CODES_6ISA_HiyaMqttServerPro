package com.mqtt.bean.userAction;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

/**
 * APP使用时长信息（不精确统计）
 ****************************
 * 统计周期：进入APP--退出APP
 * 上报时机：退出APP回到launcher时候
 ***************************
 * @author 999
 * @date 2016-06-01
 */
public class AppUserInfo extends AbsReportMsg {
	
	/**
	 * 应用包名
	 */
    private String packageName;
	
	/**
	 * 应用名称
	 */
    private String appName;
	
	/**
	 * 是否属于第三方    0是；1否
	 */
    private Integer isOtherBusi;
	
	/**
	 * 进入时间
	 */
    private String inTime;
	
	/**
	 * 退出时间  yyyy-MM-dd HH:mm:ss
	 */
    private String outTime;
    
    /**
     * 区域码
     */
    private String areaCode;

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

    public Integer isOtherBusi()
    {
        return isOtherBusi;
    }

    public void setOtherBusi(Integer isOtherBusi)
    {
        this.isOtherBusi = isOtherBusi;
    }

    public String getInTime()
    {
        return inTime;
    }

    public void setInTime(String inTime)
    {
        this.inTime = inTime;
    }

    public String getOutTime()
    {
        return outTime;
    }

    public void setOutTime(String outTime)
    {
        this.outTime = outTime;
    }

    public String getAreaCode()
    {
        return areaCode;
    }

    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }
    
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||
     *  packageName||appName||isOtherBusi||inTime||outTime||areaCode
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(packageName).append(Constants.HADOOP_COMMAS).append(appName).append(Constants.HADOOP_COMMAS).append(isOtherBusi).append(Constants.HADOOP_COMMAS).append(inTime).append(Constants.HADOOP_COMMAS).append(outTime).append(Constants.HADOOP_COMMAS).append(areaCode);
        String result=bu.toString();
        bu = null;
        return result;
    }
    
}
