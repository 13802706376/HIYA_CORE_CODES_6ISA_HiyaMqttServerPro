package com.mqtt.bean;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.mqtt.util.DateUtil;



/**
 * 消息上报基类：定义消息的共同属性(用户行为和故障上报使用该基类)
 * @author 999
 * @param <T>
 * @date 2016-05-30
 */
public abstract class AbsReportMsg {
	
	/**
	 * 消息编码
	 */
	private Integer msgCode;
	
	/**
	 * 消息描述
	 */
	private String msgExplain;
	
	/**
	 * 消息发生时间
	 */
	private String occurTime;
	
	/**
	 * MAC地址
	 */
	private String mac;

    public Integer getMsgCode()
    {
        return msgCode;
    }

    public void setMsgCode(Integer msgCode)
    {
        this.msgCode = msgCode;
    }

    public String getMsgExplain()
    {
        return msgExplain;
    }

    public void setMsgExplain(String msgExplain)
    {
        this.msgExplain = msgExplain;
    }

    public String getOccurTime()
    {
        if(StringUtils.isNotEmpty(occurTime)&&"null".equals(occurTime))
        {
            return DateUtil.formatDateTime(new Date());
        }
        return occurTime;
    }

    public void setOccurTime(String occurTime)
    {
        this.occurTime = occurTime;
    }

    public String getMac()
    {
        return mac;
    }

    public void setMac(String mac)
    {
        this.mac = mac;
    }
	
}
