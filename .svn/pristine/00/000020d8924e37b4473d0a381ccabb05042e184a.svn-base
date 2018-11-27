package com.mqtt.bean.userAction;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

/**
 * 双向网络消息
 * 
 ***********************************
 * 注意：所有URL跳转上报可能数据传输量较大
 ***********************************
 * @author 999
 * @date 2016-06-01
 */
public class NetMsg extends AbsReportMsg {
	
	
	/**
	 * 跳转URL：需要包含协议地址
	 */
	private String stepUrl;
	
	/**
	 * 鉴权结果:0-不需要鉴权；1-鉴权通过；2-鉴权失败
	 */
	private Integer authResult;


    public String getStepUrl()
    {
        return stepUrl;
    }

    public void setStepUrl(String stepUrl)
    {
        this.stepUrl = stepUrl;
    }

    public Integer isAuthResult()
    {
        return authResult;
    }

    public void setAuthResult(Integer authResult)
    {
        this.authResult = authResult;
    }
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||
     *  stepUrl||authResult
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(stepUrl).append(Constants.HADOOP_COMMAS).append(authResult);
        String result=bu.toString();
        bu = null;
        return result;
    }

}
