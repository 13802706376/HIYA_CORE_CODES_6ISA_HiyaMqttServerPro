package com.mqtt.bean.userAction;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;



/**
 * 节目预约消息（点事件）
 ***************************************
 * 用户行为上报时机：进行频道预约、预约频道到期
 ***************************************
 * @author 999
 * @String 2016-06-01
 */
public class OrderProgramMsg extends AbsReportMsg {
	
	/**
	 * 节目名称
	 */
	private String programName;
	
	/**
	 * 所在频道信息
	 */
	private String channelName;
	
	/**
	 * 频道serviceId
	 */
	private Integer serviceId;
	
	/**
	 * 节目开始时间
	 */
	private String startTime;
	
	/**
	 * 节目结束时间
	 */
	private String endTime;

    public String getProgramName()
    {
        return programName;
    }

    public void setProgramName(String programName)
    {
        this.programName = programName;
    }

    public String getChannelName()
    {
        return channelName;
    }

    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }

    public Integer getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(Integer serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||
     *  programName||channelName||serviceId||startTime||endTime
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(programName).append(Constants.HADOOP_COMMAS).append(channelName).append(Constants.HADOOP_COMMAS).append(serviceId).append(Constants.HADOOP_COMMAS).append(startTime).append(Constants.HADOOP_COMMAS).append(endTime);
        String result=bu.toString();
        bu = null;
        return result;
    }
	
}
