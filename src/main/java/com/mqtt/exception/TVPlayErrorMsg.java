package com.mqtt.exception;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.bean.userAction.ChannelInfo;
import com.mqtt.util.Constants;

/**
 * 电视播放故障消息
 ***********************故障类型************************
 *  信号丢失
 *  CA被拔出||未插入CA卡
 *  CA未配对
 *  频道授权过期
 *  频道未订购开通
 *  节目停播或者信号检修
 *  CA解扰失败
 *  获取音视频PID、ECMPID失败
 *  内部（未知）错误
 * @author 999
 *
 */
public class TVPlayErrorMsg extends AbsReportMsg
{
    /**
     * 错误码
     */
    private Integer errorCode;
    
    /**
     * 错误描述
     */
    private String errorExplain;
    
    /**
     * 频道信息
     */
    private ChannelInfo channelInfo;

    public Integer getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorExplain()
    {
        return errorExplain;
    }

    public void setErrorExplain(String errorExplain)
    {
        this.errorExplain = errorExplain;
    }

    public ChannelInfo getChannelInfo()
    {
        return channelInfo;
    }

    public void setChannelInfo(ChannelInfo channelInfo)
    {
        this.channelInfo = channelInfo;
    }
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||errorCode||errorExplain||
     *  playFre||channelName||serviceId||isRadio
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu = new StringBuffer();
        bu.append(getMsgCode() ).append( Constants.HADOOP_COMMAS ).append( getMsgExplain() ).append( Constants.HADOOP_COMMAS ).append( getOccurTime() ).append( Constants.HADOOP_COMMAS ).append( getMac() ).append( Constants.HADOOP_COMMAS);
        bu.append(errorCode ).append( Constants.HADOOP_COMMAS ).append( errorExplain ).append( Constants.HADOOP_COMMAS);
        
        if (getChannelInfo() != null)
        {
            bu.append(getChannelInfo().getPlayFre() ).append( Constants.HADOOP_COMMAS ).append( getChannelInfo().getChannelName() ).append( Constants.HADOOP_COMMAS ).append( getChannelInfo().getServiceId() ).append( Constants.HADOOP_COMMAS ).append( getChannelInfo().getIsRadio());
        }
        else
        {
            bu.append("null||null||null||null||null");
        }
        String result = bu.toString();
        bu = null;
        return result;
    }
}
