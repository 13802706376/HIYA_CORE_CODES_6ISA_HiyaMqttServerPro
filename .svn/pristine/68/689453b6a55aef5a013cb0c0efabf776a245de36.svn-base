package com.mqtt.exception;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

/**
 * 
 * App异常故障消息（点事件）
 ***********************************************
 * 堆栈异常上传FTP服务器
 * 异常日志名称规则：time/(mac ).append( occurTime).log
 ***********************************************
 * @author 999
 * @date 2016-06-01
 */
public class AppExceptionMsg extends AbsReportMsg {
	
    
    /**
     * 错误码
     */
    private Integer errorCode;
   
    /**
     * 错误描述
     */
    private String errorExplain;
    
	/**
	 * 发生异常线程名称
	 */
	private  String threadName;
	
	/**
	 * 应用名称
	 */
	private  String appName;
	

	/**
	 * 异常日志存放路径
	 */
	private String url;


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


    public String getThreadName()
    {
        return threadName;
    }


    public void setThreadName(String threadName)
    {
        this.threadName = threadName;
    }


    public String getAppName()
    {
        return appName;
    }


    public void setAppName(String appName)
    {
        this.appName = appName;
    }


    public String getUrl()
    {
        return url;
    }


    public void setUrl(String url)
    {
        this.url = url;
    }
    
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||errorCode||errorExplain||threadName||appName||url
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(errorCode).append(Constants.HADOOP_COMMAS).append(errorExplain).append(Constants.HADOOP_COMMAS).append(threadName).append(Constants.HADOOP_COMMAS).append(appName).append(Constants.HADOOP_COMMAS).append(url);
        String result=bu.toString();
        bu = null;
        return result;
    }
	
	
}
