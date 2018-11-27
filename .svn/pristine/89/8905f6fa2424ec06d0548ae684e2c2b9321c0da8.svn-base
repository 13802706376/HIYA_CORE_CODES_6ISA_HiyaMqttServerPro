package com.mqtt.bean;

/**
 * 数据上报类型： 各个模块APK 上报消息到  终端管理APK
 * 包括用户行为、使用故障 （设备信息采集、升级在终端管理APK中实现，不做APK间交互）
 * 使用规范：用户行为消息标志范围【0x001-0x0ff】;故障消息标志范围【0x101-0x1ff】
 * @author 999
 * @date 2016-05-30
 */
public class ReportMsgType {
	
	/**
	 * 设备认证消息类型
	 */
	public static final int REGISTER_DEVICE = 0x000;
	
	/**
	 * 设备状态信息
	 */
	public static final int INFO_DEVICE_STATU = 0x0f0;

	/**
	 * 用户行为：开机消息
	 */
	public static final int INFO_POWER_CONTINUE = 0x001;
	
	/**
	 * 用户行为：待机消息
	 */
	public static final int INFO_STAND_BY = 0x002;
	
	/**
	 * 用户行为：电视直播消息
	 */
	public static final int INFO_TV_PLAY = 0x003;
	
	/**
	 * 用户行为：VOD 点播消息
	 */
	public static final int INFO_VOD_PLAY = 0x004;
	
	/**
	 * 用户行为搜台消息
	 */
	public static final int INFO_SEARCH_CHANNEL = 0x005;
	
	/**
	 * app使用情况
	 */
	public static final int INFO_APP_USE = 0x006;
	
	/**
	 * 用户行为：双向页面跳转消息
	 */
	public static final int INFO_NET_STEP = 0x007;
	
	/**
	 * 用户行为：频道收藏
	 */
	public static final int INFO_FAV_CHANNEL = 0x008;
	
	/**
	 * 用户行为：节目预约
	 */
	public static final int INFO_ORDER_PROGRAM = 0x009;
	
	/**
	 * launcher-app导航跳转
	 */
	public static final int INFO_NAVIGAT_SETP = 0x00a;
	
	
	/**
	 * 故障：VOD点播
	 */
	public static final int ERROR_VOD_PLAY = 0x110;
	
	/**
     * 故障：电视直播
     */
    public static final int ERROR_TV_PLAY = 0x111;
    
	/**
	 * 故障：双向页面跳转
	 */
	public static final int ERROR_NET_STEP = 0x112;
	
	/**
	 * app异常消息
	 */
	public static final int ERROR_APP_EXCEPTION=0x113;
	
	
}
