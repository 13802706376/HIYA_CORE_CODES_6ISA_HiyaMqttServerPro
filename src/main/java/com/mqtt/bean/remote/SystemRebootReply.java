package com.mqtt.bean.remote;


/**
 * 重启回复消息
 * @author binggu
 *
 */
public class SystemRebootReply extends OrderCommon
{
    /**
     * 响应码
     */
    private Integer resultCode;
    /**
     * 结果消息
     */
    private String resultMsg;
    public Integer getResultCode()
    {
        return resultCode;
    }
    public void setResultCode(Integer resultCode)
    {
        this.resultCode = resultCode;
    }
    public String getResultMsg()
    {
        return resultMsg;
    }
    public void setResultMsg(String resultMsg)
    {
        this.resultMsg = resultMsg;
    }  
    
}
