package com.mqtt.exception;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

/**
 * VOD点播故障
 * *****************故障类型***********************
 *  前端报文响应错误信息（错误码已经定义）
 *  网络链接丢失错误信息（错误码已经定义）
 *  报文解析失败【ERROR_VOD_PARSE = 0x1101】
 *  时移点播串获取失败【ERROR_VOD_PLAYURL = 0x1103】
 *  锁频失败信息【ERROR_VOD_LOCK = 0x1102】
 *  获取音视频PID失败【ERROR_VOD_PID = 0x1104】
 *  内部（未知）错误【ERROR_UNKNOWN = 0x1100】
 ********************************
 * @author 999
 * @date 2016-07-01
 */
public class VODPlayErrorMsg extends AbsReportMsg
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
     * 播放内容资产ID
     */
    private String assetId;
    
    /**
     * 点播类型：0-rtsp;1-isa
     */
    private Integer vodType;
    
    /**
     * 是否时移：true-时移；false-点播
     * 0:false
     * 1:true
     */
    private Integer isTimeShift ;

   

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

    public String getAssetId()
    {
        return assetId;
    }

    public void setAssetId(String assetId)
    {
        this.assetId = assetId;
    }

    public Integer getVodType()
    {
        return vodType;
    }

    public void setVodType(Integer vodType)
    {
        this.vodType = vodType;
    }

    public Integer getIsTimeShift()
    {
        return isTimeShift;
    }

    public void setIsTimeShift(Integer isTimeShift)
    {
        this.isTimeShift = isTimeShift;
    }
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||errorCode||errorExplain||
     *  assetId||vodType||isTimeShift
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu = new StringBuffer();
        bu.append(getMsgCode() ).append( Constants.HADOOP_COMMAS ).append( getMsgExplain() ).append( Constants.HADOOP_COMMAS ).append( getOccurTime() ).append( Constants.HADOOP_COMMAS ).append( getMac() ).append( Constants.HADOOP_COMMAS);
        bu.append(errorCode ).append( Constants.HADOOP_COMMAS ).append( errorExplain ).append( Constants.HADOOP_COMMAS);
        
        bu.append(assetId).append(Constants.HADOOP_COMMAS).append(vodType).append(Constants.HADOOP_COMMAS).append(isTimeShift);
       
        String result = bu.toString();
        bu = null;
        return result;
    }
    
}
