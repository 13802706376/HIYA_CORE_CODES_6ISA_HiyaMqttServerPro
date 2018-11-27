package com.mqtt.exception;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

public class NetErrorMsg extends AbsReportMsg
{
    /**
     * 错误码
     */
    private int errorCode;  
    
    /**
     * 错误描述
     */
    private String errorExplain; 

    /**
     * 出错url
     */
    private String stepUrl;

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
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

    public String getStepUrl()
    {
        return stepUrl;
    }

    public void setStepUrl(String stepUrl)
    {
        this.stepUrl = stepUrl;
    }
    
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||errorCode||errorExplain||stepUrl
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(errorCode).append(Constants.HADOOP_COMMAS).append(errorExplain).append(Constants.HADOOP_COMMAS).append(stepUrl);
        String result=bu.toString();
        bu = null;
        return result;
    }

}
