package com.mqtt.bean.userAction;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

/**
 * 收藏频道统计信息(点事件)
 ***************************
 * 用户行为上报时机：收藏、取消收藏
 ***************************
 * @author 999
 * @date 2016-06-01
 */
public class FavChannelMsg extends AbsReportMsg {
	
	/**
	 * 频道名称
	 */
	private String channelName;
	
	/**
	 * serviceId
	 */
	private Integer serviceId;
	
	/**
	 * 所在频点
	 */
	private Integer fre;

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

    public Integer getFre()
    {
        return fre;
    }

    public void setFre(Integer fre)
    {
        this.fre = fre;
    }
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||
     *  channelName||serviceId||fre
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(channelName).append(Constants.HADOOP_COMMAS).append(serviceId).append(Constants.HADOOP_COMMAS).append(fre);
        String result=bu.toString();
        bu = null;
        return result;
    }
	
}
