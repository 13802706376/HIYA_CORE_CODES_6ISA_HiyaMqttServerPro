package com.mqtt.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * @author Weimmy
 * 
 * @date:2011-7-26
 * @version :1.0
 * 
 */

public class SystemProperties {

    public static final String PROPERTY_FILE_NAME = "system.properties";
    public static Logger log=LogManager.getLogger("SystemProperties");
    private static CompositeConfiguration config = null;
    private static PropertiesConfiguration propConfig = null;

    
     public static void init(String path)
     {
            try
            {
                config = new CompositeConfiguration();
                propConfig = new PropertiesConfiguration();
                InputStream in = new FileInputStream(path);
                propConfig.load(in);
                propConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
                config.addConfiguration(propConfig);
                InitLog4j2XmlPath();
            }
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (ConfigurationException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
     }

    
    public static void InitLog4j2XmlPath()
    {
        String xmlPath=SystemProperties.getProperty("log4j2XmlPath","/usr/mqtt/config/log4j2.xml");
        File file  = new File(xmlPath);
        URL url;
       System.out.println(file.exists());
        try
        {
         url = file.toURI().toURL();
         //改变系统参数
         System.setProperty("log4j.configurationFile",url.toString());
         //重新初始化Log4j2的配置上下文
         LoggerContext context =(LoggerContext)LogManager.getContext(false);
         context.reconfigure();
        }
        catch (MalformedURLException e)
        {
        log.error(e);
        }
    }
    public static void main(String[] args)
    {
        InitLog4j2XmlPath();
    }
    
    public static String getProperty(String property, String defaultValue) {
        String retStr = "";
        if (config != null) {
            retStr = config.getString(property, defaultValue);
        }
        return retStr;
    }

    public static String getProperty(String property) {
        String retStr = "";
        if (config != null) {
            retStr = config.getString(property, "");
        }
        return retStr;
    }

    public static Integer getInt(String property) {
        Integer result = 0;
        if (config != null) {
            result = config.getInt(property, 0);
        }
        return result;
    }

    public static Integer getInt(String property, Integer defaultValue) {
        Integer result = 0;
        if (config != null) {
            result = config.getInt(property, defaultValue);
        }
        return result;
    }

    public static void setProperty(String strKey, String strValue) {
        if (config != null) {
            config.setProperty(strKey, strValue);
        }
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getList(String property, List defaultValue) {
        List result = new ArrayList();
        if (config != null) {
            result = config.getList(property, defaultValue);
        }
        return result;
    }

    public static void save() throws ConfigurationException {
        if (config != null) {
            PropertiesConfiguration propConfig = (PropertiesConfiguration)config.getConfiguration(0);
            propConfig.save();
        }
    }
	
}
