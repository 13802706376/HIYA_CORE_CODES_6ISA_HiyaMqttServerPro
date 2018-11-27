package com.mqtt.bean.forStb;

import java.util.List;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

/**
 * 终端管理注册
 * @author 999
 * @date 2016-06-21
 */
public class RegistMsg extends AbsReportMsg{
	
	/**
	 * 终端设备型号
	 */
    private String stbType; 
    
    /**
     * 终端设备序列号
     */
    private String sn;
	
	/**
	 * 厂商
	 */
	private String maunName;
	
	/**
	 * 厂商认证号
	 */
	private String maunCode;
	
	/**
	 * 硬件版本号
	 */
	private String hardwareVersion;
	
	/**
	 * TVN
	 */
	private String tvn;
	
	/**
	 * ip地址
	 */
	private String ip;
	
	/**
	 * 区域码
	 */
	private String areaCode;
	
	/**
	 * 操作系统版本
	 */
	private String osVersion; 
	
	/**
	 * rom版本
	 */
	private String romVersion;
	
	/**
	 * CA info
	 */
	private CAInfo caInfo;
	
	/**
	 * APP 版本信息
	 */
	private List<APPInfo> appInfoList;

    public String getStbType()
    {
        return stbType;
    }

    public void setStbType(String stbType)
    {
        this.stbType = stbType;
    }

    public String getMaunName()
    {
        return maunName;
    }

    public void setMaunName(String maunName)
    {
        this.maunName = maunName;
    }

    public String getMaunCode()
    {
        return maunCode;
    }

    public void setMaunCode(String maunCode)
    {
        this.maunCode = maunCode;
    }

    public String getHardwareVersion()
    {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion)
    {
        this.hardwareVersion = hardwareVersion;
    }

    public String getTvn()
    {
        return tvn;
    }

    public void setTvn(String tvn)
    {
        this.tvn = tvn;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getAreaCode()
    {
        return areaCode;
    }

    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }


    public String getOsVersion()
    {
        return osVersion;
    }

    public void setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }

    public String getRomVersion()
    {
        return romVersion;
    }

    public void setRomVersion(String romVersion)
    {
        this.romVersion = romVersion;
    }

    public CAInfo getCaInfo()
    {
        return caInfo;
    }

    public void setCaInfo(CAInfo caInfo)
    {
        this.caInfo = caInfo;
    }

    public List<APPInfo> getAppInfoList()
    {
        return appInfoList;
    }

    public void setAppInfoList(List<APPInfo> appInfoList)
    {
        this.appInfoList = appInfoList;
    }
    
	
    public String getSn()
    {
        return sn;
    }

    public void setSn(String sn)
    {
        this.sn = sn;
    }

    /**
     * 拼写规则：
     * msgCode||msgExplain||occurTime||deviceMac||stbType||sn||maunName||maunCode||hardwareVersion||osVersion||
     * ip||tvn||areaCode||caCode||caType||caVersion||romVersion||packageName,appName,
     * versionCode,versionName,isThirdApp;packageName,appName,versionCode,versionName,isThirdApp
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(stbType).append(Constants.HADOOP_COMMAS).append(sn).append(Constants.HADOOP_COMMAS).append(maunName).append(Constants.HADOOP_COMMAS).append(maunCode).append(Constants.HADOOP_COMMAS).append(hardwareVersion).append(Constants.HADOOP_COMMAS).append(osVersion).append(Constants.HADOOP_COMMAS);
        bu.append(ip).append(Constants.HADOOP_COMMAS).append(tvn).append(Constants.HADOOP_COMMAS).append(areaCode).append(Constants.HADOOP_COMMAS);
        if(getCaInfo()==null)
        {
            bu.append("null||null||null||");
        }else
        {
            bu.append(getCaInfo().getCaCode()).append(Constants.HADOOP_COMMAS).append(getCaInfo().getCaType()).append(Constants.HADOOP_COMMAS).append(getCaInfo().getCaVersion()).append(Constants.HADOOP_COMMAS);
        }
        bu.append(romVersion).append(Constants.HADOOP_COMMAS);
        if(getAppInfoList()==null||getAppInfoList().size()==0)
        {
            bu.append("null");
        }else
        {
            /**
             * 添加app节点
             */
            for(APPInfo app:getAppInfoList())
            {
                bu.append(app.getPackageName()).append(",").append(app.getAppName()).append(",").append(app.getVersionCode()).append(","
                        ).append(app.getVersionName()).append(",").append(app.getIsThirdApp()).append(";");
            }
        }
        if(bu.length()>0)
        	bu.deleteCharAt(bu.length()-1);
        String result=bu.toString();
        bu = null;
        return result;
    }
}
