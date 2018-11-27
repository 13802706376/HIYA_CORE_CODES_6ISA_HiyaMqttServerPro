package com.mqtt.bean.userAction;

import com.mqtt.bean.AbsReportMsg;
import com.mqtt.util.Constants;

/**
 * 搜台上报信息
 ***************************************
 * 用户行为统计周期：搜索开始-搜索结束
 * 用户行为上报时机：搜索结束
 ***************************************
 * @author 999
 * @String 2016-06-01
 */
public class SearchMsg extends AbsReportMsg {

	/**
	 * 搜索类型：1-自动搜索；2-手动搜索；3-全频点搜索
	 */
	private  Integer searchType;
	
	/**
	 * 进入搜索时间
	 */
	private String inTime;
	
	/**
	 * 搜索结束时间
	 */
	private String outTime;
	
	/**
	 * 搜索参数信息
	 * @author 999
	 */
	private SearchParam searchParam;
	
	
	/**
	 * 搜索结果
	 */
	private SearchResult searchResult;


    public Integer getSearchType()
    {
        return searchType;
    }


    public void setSearchType(Integer searchType)
    {
        this.searchType = searchType;
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


    public SearchParam getSearchParam()
    {
        return searchParam;
    }


    public void setSearchParam(SearchParam searchParam)
    {
        this.searchParam = searchParam;
    }


    public SearchResult getSearchResult()
    {
        return searchResult;
    }


    public void setSearchResult(SearchResult searchResult)
    {
        this.searchResult = searchResult;
    }
	
    /**
     * 拼写规则
     *  msgCode||msgExplain||occurTime||deviceMac||
     *  searchType||inTime||outTime||
     *  mainFre||secondFre||startFre||endFre||symbolRate||qam||
     *  countFre||countVideo||countRadia||countOther
     * @return
     */
    public String toLogString()
    {
        StringBuffer bu=new StringBuffer();
        bu.append(getMsgCode()).append(Constants.HADOOP_COMMAS).append(getMsgExplain()).append(Constants.HADOOP_COMMAS).append(getOccurTime()).append(Constants.HADOOP_COMMAS).append(getMac()).append(Constants.HADOOP_COMMAS);
        bu.append(searchType).append(Constants.HADOOP_COMMAS).append(inTime).append(Constants.HADOOP_COMMAS).append(outTime).append(Constants.HADOOP_COMMAS);
        if(this.getSearchParam()!=null)
        {
            SearchParam param=this.getSearchParam();
            bu.append(param.getMainFre()).append(Constants.HADOOP_COMMAS).append(param.getSecondFre()).append(Constants.HADOOP_COMMAS).append(param.getStartFre()).append(Constants.HADOOP_COMMAS).append(param.getEndFre()).append(Constants.HADOOP_COMMAS).append(param.getSymbolRate()).append(Constants.HADOOP_COMMAS).append(param.getQam()).append(Constants.HADOOP_COMMAS) ;
        }else
        {
            bu.append("null||null||null||null||null||null||");
        }
        
        if(this.getSearchResult()!=null)
        {
            SearchResult re=this.getSearchResult();
            bu.append(re.getCountFre()).append(Constants.HADOOP_COMMAS).append(re.getCountVideo()).append(Constants.HADOOP_COMMAS).append(re.getCountRadia()).append(Constants.HADOOP_COMMAS).append(re.getCountOther());
        }else
        {
            bu.append("null||null||null||null");
        }
        
        String result=bu.toString();
        bu = null;
        return result;
    }
	
	
}
