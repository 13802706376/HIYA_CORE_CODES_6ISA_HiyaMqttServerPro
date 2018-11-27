package com.mqtt.bean.userAction;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;
import com.mqtt.util.DateUtil;

/**
 * 开机-关机消息
 * 
 ************************************
 * 用户行为统计周期：开机-关机
 * 用户行为上报时机：第二次开机启动
 ************************************
 * @author 999
 * @String 2016-06-01
 */
public class PowerOnMsg extends AbsReportMsg {

	/**    
	 * 开机时间
	 */
    private  String powerOnTime;
	
	/**
	 * 关机时间
	 */
    private  String powerOffTime;

    public String getPowerOnTime()
    {
        if(StringUtils.isNotEmpty(powerOnTime)&&"null".equals(powerOnTime))
        {
            return DateUtil.formatDateTime(new Date());
        }
        return powerOnTime;
    }

    public void setPowerOnTime(String powerOnTime)
    {
        this.powerOnTime = powerOnTime;
    }

    public String getPowerOffTime()
    {
        return powerOffTime;
    }

    public void setPowerOffTime(String powerOffTime)
    {
        this.powerOffTime = powerOffTime;
    }
    
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||
     *  powerOnTime||powerOffTime
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(powerOnTime).append(Constants.HADOOP_COMMAS).append(powerOffTime);
        String result=bu.toString();
        bu = null;
        return result;
    }
}
