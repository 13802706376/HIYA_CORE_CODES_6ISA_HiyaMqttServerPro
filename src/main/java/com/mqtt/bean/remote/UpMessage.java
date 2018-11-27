package com.mqtt.bean.remote;

public class UpMessage
{
    private String mac;
    private String planId;
    private Integer msgCode;
    //0-发送未达到，1-发送已达到，2-执行成功  （未发送或者mac地址未找到，不返回）
    private Integer status;
    private String obj;
    public String getMac()
    {
        return mac;
    }
    public void setMac(String mac)
    {  
        this.mac = mac;
    }
    
    
    public String getObj()
    {
        return obj;
    }
    public void setObj(String obj)
    {
        this.obj = obj;
    }
    public Integer getMsgCode()
    {
        return msgCode;
    }
    public void setMsgCode(Integer msgCode)
    {
        this.msgCode = msgCode;
    }
    public Integer getStatus()
    {
        return status;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    public String getPlanId()
    {
        return planId;
    }
    public void setPlanId(String planId)
    {
        this.planId = planId;
    }
}
