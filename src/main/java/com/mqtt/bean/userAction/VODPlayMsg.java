package com.mqtt.bean.userAction;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

/**
 * Vod点播消息
 * 
 ********************************************
 *	用户行为统计周期：点播开始-点播结束
 *	用户行为上报时机：退出时候进行上报（方便统计观看影片总时长）
 ********************************************
 * @author 999
 * @String 2016-05-30
 */
public class VODPlayMsg extends AbsReportMsg {
	
	
	/**
	 * 播放影片资产ID
	 */
    private String assetId;
	
	/**
	 * 点播区域码
	 */
    private String areaCode;
	
	/**
	 * 点播类型：0-rtsp;1-isa
	 */
    private int vodType;
	
	/**
	 * 是否时移：true-时移；false-点播
	 */
    private Integer isTimeShift;
	
	/**
	 * 会话控制请求地址
	 */
    private String sspAddr;
	
	/**
	 * 推流请求地址
	 */
    private String vsAddr;
	
	/**
	 * 点播分配频点信息
	 */
    private int playFre;
    
    /**
     * playUrl 节点id
     */
    private  String nodeId;
	
	/**
	 * 分配serviceId
	 */
	private int serviceId;
	
	/**
	 * 影片总时长
	 */
	private int totalTime; 
	
	/**
	 * 开始播放位置
	 */
	private long startTime;
	
	/**
	 * 播放进入时间
	 */
	private String inTime;
	
	/**
	 * 播放退出时间
	 */
	private String outTime;

    

    public String getAssetId()
    {
        return assetId;
    }

    public void setAssetId(String assetId)
    {
        this.assetId = assetId;
    }

    public String getAreaCode()
    {
        return areaCode;
    }

    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public int getVodType()
    {
        return vodType;
    }

    public void setVodType(int vodType)
    {
        this.vodType = vodType;
    }

    public Integer isTimeShift()
    {
        return isTimeShift;
    }

    public void setTimeShift(Integer isTimeShift)
    {
        this.isTimeShift = isTimeShift;
    }

    public String getSspAddr()
    {
        return sspAddr;
    }

    public void setSspAddr(String sspAddr)
    {
        this.sspAddr = sspAddr;
    }

    public String getVsAddr()
    {
        return vsAddr;
    }

    public void setVsAddr(String vsAddr)
    {
        this.vsAddr = vsAddr;
    }

    public int getPlayFre()
    {
        return playFre;
    }

    public void setPlayFre(int playFre)
    {
        this.playFre = playFre;
    }

    public int getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(int serviceId)
    {
        this.serviceId = serviceId;
    }

    public int getTotalTime()
    {
        return totalTime;
    }

    public void setTotalTime(int totalTime)
    {
        this.totalTime = totalTime;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public String getInTime()
    {
        return inTime;
    }

    public void setInTime(String inTime)
    {
        this.inTime = inTime;
    }

    public String getOutTime()
    {
        return outTime;
    }

    public void setOutTime(String outTime)
    {
        this.outTime = outTime;
    }
    
    
    
    public Integer getIsTimeShift()
    {
        return isTimeShift;
    }

    public void setIsTimeShift(Integer isTimeShift)
    {
        this.isTimeShift = isTimeShift;
    }

    public String getNodeId()
    {
        return nodeId;
    }

    public void setNodeId(String nodeId)
    {
        this.nodeId = nodeId;
    }

    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||
     *  assetId||nodeId||areaCode||vodType||isTimeShift||sspAddr||vsAddr||playFre||serviceId||totalTime
     *  startTime||inTime||outTime
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(assetId).append(Constants.HADOOP_COMMAS).append(nodeId).append(Constants.HADOOP_COMMAS).append(areaCode).append(Constants.HADOOP_COMMAS).append(vodType).append(Constants.HADOOP_COMMAS).append(isTimeShift).append(Constants.HADOOP_COMMAS).append(sspAddr).append(Constants.HADOOP_COMMAS).append(vsAddr).append(Constants.HADOOP_COMMAS).append(playFre
                ).append(Constants.HADOOP_COMMAS).append(serviceId).append(Constants.HADOOP_COMMAS).append(totalTime).append(Constants.HADOOP_COMMAS).append(startTime).append(Constants.HADOOP_COMMAS).append(inTime).append(Constants.HADOOP_COMMAS).append(outTime);
        String result=bu.toString();
        bu = null;
        return result;
    }
	
}






