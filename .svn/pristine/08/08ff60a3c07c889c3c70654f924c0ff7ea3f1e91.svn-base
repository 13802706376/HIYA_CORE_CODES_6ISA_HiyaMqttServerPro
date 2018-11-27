package com.mqtt.bean.userAction;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

/**
 * 电视播放上报消息
 * 
 ***************************************************
 *	用户行为统计周期：切入频道-切出频道   
 *	用户行为上报时机：切出时候进行上报（方便统计播放总时长）
 *	用户行为统计上报前提：需要持续播放两分钟（防止快速切台导致大量上报信息）
 ***************************************************
 * @author 999
 * @date 2016-05-31
 */
public class TVPlayMsg extends AbsReportMsg {

    
    /**
     * 切入：0;切出:1
     */
    private Integer actionType;
    
    /**
     * 动作之间是相互匹配的，每次切入生成一个唯一Code，切出时候要携带
     */
    private String actionCode;
    
    
    /**
     * 区域码
     */
    private String areaCode;
    
    /**
     * 时长
     */
    private Long duration;
    
    
	/**
	 * 频道信息
	 */
	private  ChannelInfo channelInfo;
	

    public ChannelInfo getChannelInfo()
    {
        return channelInfo;
    }

    public void setChannelInfo(ChannelInfo channelInfo)
    {
        this.channelInfo = channelInfo;
    }

    public Integer getActionType()
    {
        return actionType;
    }

    public void setActionType(Integer actionType)
    {
        this.actionType = actionType;
    }

    public String getActionCode()
    {
        return actionCode;
    }

    public void setActionCode(String actionCode)
    {
        this.actionCode = actionCode;
    }
    
    public String getAreaCode()
    {
        return areaCode;
    }

    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public Long getDuration()
    {
        return duration;
    }

    public void setDuration(Long duration)
    {
        this.duration = duration;
    }

    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||
     *  actionType||actionCode||duration||areaCode
     *  playFre||channelName||serviceId||isRadio
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(actionType).append(Constants.HADOOP_COMMAS).append(actionCode).append(Constants.HADOOP_COMMAS).append(duration).append(Constants.HADOOP_COMMAS).append(areaCode).append(Constants.HADOOP_COMMAS);
        if(this.getChannelInfo()!=null)
        {
            ChannelInfo ch=this.getChannelInfo();
            bu.append(ch.getPlayFre()).append(Constants.HADOOP_COMMAS).append(ch.getChannelName()).append(Constants.HADOOP_COMMAS).append(ch.getServiceId()).append(Constants.HADOOP_COMMAS).append(ch.getIsRadio());
        }else
        {
            bu.append("null||null||null||null");
        }
        String result=bu.toString();
        bu = null;
        return result;
    }
}
