package com.mqtt.util;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * @author Zhong.Zongming
 * 
 */
public class SysConfig
{

	private static final Logger log = LogManager.getLogger(SysConfig.class);
	private static PropertiesConfiguration sysProp = null;

	public static void init(String proPath,String logPath)
	{
		ClassLoader classLoader = SysConfig.class.getClassLoader();
		try
		{
			//URL resource = classLoader.getResource("resources/system.properties");
			//String path = resource.getPath();
			
			sysProp = new PropertiesConfiguration(proPath);
			sysProp.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (ConfigurationException e)
		{
			log.error("Load Error Information Failed");
		}

		
		//InputStream ips2 = SysConfig.class.getClassLoader() .getResourceAsStream("/log4j2.xml");
		
		//URL resourceLog4j = classLoader.getResource("log4j2.xml");
		//String pathLog4j = resourceLog4j.getPath();
		File file = new File(logPath);
		URL url;
		System.out.println(file.exists());
		try
		{
			url = file.toURI().toURL();
			// 改变系统参数
			System.setProperty("log4j.configurationFile", url.toString());
			// 重新初始化Log4j2的配置上下文
			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			context.reconfigure();
		} catch (MalformedURLException e)
		{
			log.error(e);
		}

	}

	private SysConfig()
	{

	}

	/*
	 * public static String getSystemConfigReload(String configCode,String
	 * defaultStr){ try { properties =
	 * ResourcesUtil.getResourceAsProperties(FILE_NAME); return
	 * properties.getProperty(configCode, "Unkonw ConfigCode:" + configCode); }
	 * catch (IOException e) { } return defaultStr; }
	 */

	public static String getSystemConfig(String configCode)
	{
		String retStr = "";
		String[] tmp = null;
		if (sysProp != null)
		{
			tmp = sysProp.getStringArray(configCode);
			if (tmp != null && tmp.length > 1)
			{
				for (String t : tmp)
				{
					retStr = retStr + t + ",";
				}
				retStr = retStr.substring(0, retStr.length() - 1);
			} else if (tmp.length == 1)
			{
				retStr = tmp[0];
			} else
				retStr = "";

			/*
			 * retStr =
			 * (String)sysProp.getProperty(configCode);//.getString(configCode,
			 * ""); if(StringUtils.isNotEmpty(retStr)){ retStr =
			 * retStr.replaceAll(" ",""); retStr = retStr.replaceAll("\\[","");
			 * retStr = retStr.replaceAll("\\]",""); }else retStr = "";
			 */
			// retStr = sysProp.getString(configCode, "");
		}

		return retStr;
	}

	public static void main(String[] args)
	{
		System.out.println(SysConfig.getSystemConfig("driverClass"));
	}
}