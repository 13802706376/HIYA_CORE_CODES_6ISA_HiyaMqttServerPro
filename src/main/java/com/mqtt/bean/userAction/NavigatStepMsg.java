package com.mqtt.bean.userAction;

import com.mqtt.bean.AbsReportMsg;

/**
 * launcher导航跳转信息（点事件）
 ********************************
 * 行为上报时机：launcher点击
 ********************************
 * @author 999
 * @date 2016-06-01
 */
public class NavigatStepMsg extends AbsReportMsg {
	
    
    /**
     * 包名
     */
    private String packageName;
    
    /**
     * app名
     */
    private String appName;
    
	
	/**
	 * 是否属于第三方业务 0是第三方，1是自有
	 */
	private Integer isOtherBusi;
	
	/**
	 * 跳转地址
	 */
	private String stepUrl;
	
	/**
	 * 鉴权结果:0-不需要鉴权；1-鉴权通过；2-鉴权失败
	 */
	private int authResult;

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }


    public Integer isOtherBusi()
    {
        return isOtherBusi;
    }

    public void setOtherBusi(Integer isOtherBusi)
    {
        this.isOtherBusi = isOtherBusi;
    }

    public String getStepUrl()
    {
        return stepUrl;
    }

    public void setStepUrl(String stepUrl)
    {
        this.stepUrl = stepUrl;
    }

    public int getAuthResult()
    {
        return authResult;
    }

    public void setAuthResult(int authResult)
    {
        this.authResult = authResult;
    }
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||
     *  packageName||appName||isOtherBusi||stepUrl||authResult
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()+"||"+getMsgExplain()+"||"+getOccurTime()+"||"+getMac()+"||");
        bu.append(packageName+"||"+appName+"||"+isOtherBusi+"||"+stepUrl+"||"+authResult);
        String result=bu.toString();
        return result;
    }
	
}
