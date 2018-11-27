package com.mqtt.server;

/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageIdVariableHeader;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPubAckMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttPublishVariableHeader;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.mqtt.MqttSubAckMessage;
import io.netty.handler.codec.mqtt.MqttSubAckPayload;
import io.netty.handler.codec.mqtt.MqttSubscribeMessage;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mqtt.bean.RemoteOrderType;
import com.mqtt.bean.ReportMsgType;
import com.mqtt.bean.forStb.RegistMsg;
import com.mqtt.bean.remote.APPInfo;
import com.mqtt.bean.remote.AppUninstallReply;
import com.mqtt.bean.remote.AppUpgradeOrder;
import com.mqtt.bean.remote.AppUpgradeReply;
import com.mqtt.bean.remote.OrderCommon;
import com.mqtt.bean.remote.PlanMac;
import com.mqtt.bean.remote.PlanPac;
import com.mqtt.bean.remote.ScreenshotReplay;
import com.mqtt.bean.remote.SystemRebootReply;
import com.mqtt.bean.remote.SystemUpgradeOrder;
import com.mqtt.bean.remote.SystemUpgradeReply;
import com.mqtt.bean.remote.UpMessage;
import com.mqtt.bean.remote.UpgradeOrder;
import com.mqtt.bean.userAction.AppUserInfo;
import com.mqtt.bean.userAction.FavChannelMsg;
import com.mqtt.bean.userAction.NavigatStepMsg;
import com.mqtt.bean.userAction.NetMsg;
import com.mqtt.bean.userAction.OrderProgramMsg;
import com.mqtt.bean.userAction.PowerOnMsg;
import com.mqtt.bean.userAction.SearchMsg;
import com.mqtt.bean.userAction.TVPlayMsg;
import com.mqtt.bean.userAction.VODPlayMsg;
import com.mqtt.exception.AppExceptionMsg;
import com.mqtt.exception.NetErrorMsg;
import com.mqtt.exception.TVPlayErrorMsg;
import com.mqtt.exception.VODPlayErrorMsg;
import com.mqtt.util.Constants;
import com.mqtt.util.SystemProperties;
import com.mqtt.util.json.gson.GjsonUtil;

/**
 * @description mqtt消息处理实现类
 * @author binggu
 * @date 2016-07-20
 */
@Sharable
public class MQTTServerHandler extends SimpleChannelInboundHandler<Object>
{

	public static Logger log = LogManager.getLogger(MQTTServerHandler.class);

	private final AttributeKey<String> USER = AttributeKey.valueOf("user");
	// 区域对应的mac地址，应对区域发送
	private static Map<String, List<String>> areaMap = new ConcurrentHashMap<String, List<String>>();
	// 所有该上报的消息集合 mac+plan
	private static Map<Integer, Map<String, UpMessage>> upMap = new ConcurrentHashMap<Integer, Map<String, UpMessage>>();
	private AtomicInteger requestIdau = new AtomicInteger(1);
	// 用户对应连接，按mac地址
	private static Map<String, ChannelHandlerContext> userMap = new ConcurrentHashMap<String, ChannelHandlerContext>();
	// 管理系统 连接
	private static Map<ChannelHandlerContext, String> managerMap = new ConcurrentHashMap<ChannelHandlerContext, String>();
	// 指令下发、上报业务线程池
	public ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

	@Override
	// 连接成功后调用的方法
	public void channelActive(ChannelHandlerContext ctx) throws Exception
	{
		// // Send greeting for a new connection
		// System.out.println("Welcome to " +
		// InetAddress.getLocalHost().getHostName());
		// ctx.writeAndFlush("Welcome to " +
		// InetAddress.getLocalHost().getHostName() + "!\r\n");
		// ctx.writeAndFlush("It is " + new Date() + " now.\r\n");
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object request) throws Exception
	{
		try
		{
			MqttMessage req = (MqttMessage) request;
			MqttMessageType messageType = req.fixedHeader().messageType();
			switch (messageType)
			{
			case CONNECT:
				doConnectMessage(ctx, request);
				return;
			case SUBSCRIBE:
				doSubMessage(ctx, request);
				return;
			case PUBLISH:
				doPublishMessage(ctx, request);
				return;
			case PINGREQ:
				doPingreoMessage(ctx, request);
				return;
			case PUBACK:
				doPubAck(ctx, request);
				return;
			case PUBREC:
			case PUBREL:
			case PUBCOMP:
			case UNSUBACK:

				return;
			case PINGRESP:
				doPingrespMessage(ctx, request);
				return;
			case DISCONNECT:
				ctx.close();
				return;
			default:
				return;
			}
		} catch (Exception ex)
		{

		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx)
	{
		log.debug(ctx + "is close!");
		// 清理服务器缓存
		managerMap.remove(ctx);
		// 清理用户缓存
		if (ctx.channel().hasAttr(USER))
		{
			String user = ctx.channel().attr(USER).get();
			userMap.remove(user);
		}
	}

	/**
	 * 超时处理
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
	{
		if (evt instanceof IdleStateEvent)
		{
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state().equals(IdleState.ALL_IDLE))
			{
				log.debug("ctx heartbeat timeout,close!");// +ctx);
				ctx.fireChannelInactive();
				ctx.close();
			}
		}
		super.userEventTriggered(ctx, evt);
	}

	/**
	 * 心跳响应
	 * 
	 * @param ctx
	 * @param request
	 */
	private void doPingreoMessage(ChannelHandlerContext ctx, Object request)
	{
		// MqttMessage message=(MqttMessage)request;
		// System.out.println("响应心跳！");
		MqttFixedHeader header = new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0);
		MqttMessage pingespMessage = new MqttMessage(header);
		ctx.write(pingespMessage);
	}

	private void doPingrespMessage(ChannelHandlerContext ctx, Object request)
	{
		// System.out.println("收到心跳请求！");
	}

	/**
	 * 封装心跳请求
	 * 
	 * @param ctx
	 */
	// private void buildHearBeat(ChannelHandlerContext ctx)
	// {
	// MqttFixedHeader mqttFixedHeader=new
	// MqttFixedHeader(MqttMessageType.PINGREQ, false, MqttQoS.AT_MOST_ONCE,
	// false, 0);
	// MqttMessage message=new MqttMessage(mqttFixedHeader);
	// ctx.writeAndFlush(message);
	// }
	/**
	 * 封装发布
	 * 
	 * @param str
	 * @param topicName
	 * @return
	 */
	public static MqttPublishMessage buildPublish(String str, String topicName, Integer messageId)
	{
		MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.PUBLISH, false, MqttQoS.AT_LEAST_ONCE, false, str.length());
		MqttPublishVariableHeader variableHeader = new MqttPublishVariableHeader(topicName, messageId);// ("MQIsdp",3,false,false,false,0,false,false,60);
		ByteBuf payload = Unpooled.wrappedBuffer(str.getBytes());
		MqttPublishMessage msg = new MqttPublishMessage(mqttFixedHeader, variableHeader, payload);
		return msg;
	}

	/**
	 * 处理连接请求
	 * 
	 * @param ctx
	 * @param request
	 */
	private void doConnectMessage(ChannelHandlerContext ctx, Object request)
	{

		MqttConnectMessage message = (MqttConnectMessage) request;
		MqttConnAckVariableHeader variableheader = new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED, false);
		MqttConnAckMessage connAckMessage = new MqttConnAckMessage(Constants.CONNACK_HEADER, variableheader);
		// ctx.write(MQEncoder.doEncode(ctx.alloc(),connAckMessage));
		ctx.write(connAckMessage);
		// String user = message.variableHeader().name();
		String user = message.payload().clientIdentifier();
		log.debug("connect ,mac is :" + user);
		if (user.startsWith("SYSTEM"))
		{
			managerMap.put(ctx, user);
		} else
		{
			// 将用户信息写入变量
			if (!ctx.channel().hasAttr(USER))
			{
				ctx.channel().attr(USER).set(user);
			}
			// 将连接信息写入缓存
			userMap.put(user, ctx);
			log.debug("the user num is " + userMap.size());
		}
		// for(Object key:userMap.keySet())
		// {
		// System.out.println(key);
		// }
	}

	/**
	 * 处理 客户端订阅消息
	 * 
	 * @param ctx
	 * @param request
	 */
	private void doSubMessage(ChannelHandlerContext ctx, Object request)
	{
		MqttSubscribeMessage message = (MqttSubscribeMessage) request;
		int msgId = message.variableHeader().messageId();
		if (msgId == -1)
			msgId = 1;
		MqttMessageIdVariableHeader header = MqttMessageIdVariableHeader.from(msgId);
		MqttSubAckPayload payload = new MqttSubAckPayload(0);
		MqttSubAckMessage suback = new MqttSubAckMessage(Constants.SUBACK_HEADER, header, payload);
		ctx.write(suback);
	}

	/**
	 * 处理客户端回执消息
	 * 
	 * @param ctx
	 * @param request
	 */
	private void doPubAck(ChannelHandlerContext ctx, Object request)
	{
		MqttPubAckMessage message = (MqttPubAckMessage) request;
		log.debug(request);
		String user = ctx.channel().attr(USER).get();
		Map<String, UpMessage> requestMap = upMap.get(message.variableHeader().messageId());
		if (requestMap != null && requestMap.size() > 0)
		{
			UpMessage upmessage = requestMap.get(user);
			if (upmessage != null)
			{
				upmessage.setStatus(Constants.SENDSUCESS);
				requestMap.put(user, upmessage);
				upMap.put(message.variableHeader().messageId(), requestMap);
			}
		}
	}

	/**
	 * 处理 客户端发布消息
	 * 
	 * @param ctx
	 * @param request
	 */
	private void doPublishMessage(ChannelHandlerContext ctx, Object request)
	{
		// long time = System.currentTimeMillis();
		MqttPublishMessage message = (MqttPublishMessage) request;
		ByteBuf buf = message.payload();
		String msgg = new String(ByteBufUtil.getBytes(buf));

		int msgId = message.variableHeader().messageId();
		log.debug(msgg);
		if (msgId == -1)
			msgId = 1;
		// 主题名
		String topicName = message.variableHeader().topicName();
		/**
		 * 区分收到的发布消息，如果是服务端发送的消息，拉起业务线程池进行处理。 处理流程： 解析服务端的发布获取用户列表
		 * 根据用户列表查询可用连接转发请求，并记录发送情况上报发送情况，并拉起定时器（定 时器截止时间后将收集的处理情况上报服务端） 收集处理情况
		 */

		int orderType = getOrderType(msgg);
		// long times = System.currentTimeMillis();
		switch (orderType)
		{
		
		// 写日志。实时非实时数据等-给hadoop提供数据
		case ReportMsgType.REGISTER_DEVICE:
		{
			RegistMsg msg = GjsonUtil.fromJson(msgg, RegistMsg.class);
			List<String> macList = areaMap.get(msg.getAreaCode());
			if (macList == null)
				macList = new ArrayList<String>();
			if (!macList.contains(msg.getMac()))
			{
				macList.add(msg.getMac());
				areaMap.put(msg.getAreaCode(), macList);
			}
			String reLog = msg.toLogString();
			Constants.registLog.debug(reLog);
			Constants.realTimeRegistLog.debug(msgg);
			break;
		}
		case ReportMsgType.INFO_POWER_CONTINUE:
		{
			PowerOnMsg msg = GjsonUtil.fromJson(msgg, PowerOnMsg.class);
			String reLog = msg.toLogString();
			Constants.powerOnLog.debug(reLog);
			break;
		}
		case ReportMsgType.INFO_TV_PLAY:
		{
			TVPlayMsg msg = GjsonUtil.fromJson(msgg, TVPlayMsg.class);
			String reLog = msg.toLogString();
			Constants.tvPlayLog.debug(reLog);
			// 频道实时收视率
			Constants.realTimeTam.debug(msgg);
			break;
		}
		case ReportMsgType.INFO_VOD_PLAY:
		{
			VODPlayMsg msg = GjsonUtil.fromJson(msgg, VODPlayMsg.class);
			String reLog = msg.toLogString();
			Constants.vodPlayLog.debug(reLog);
			break;
		}
		case ReportMsgType.INFO_SEARCH_CHANNEL:
		{
			SearchMsg msg = GjsonUtil.fromJson(msgg, SearchMsg.class);
			String reLog = msg.toLogString();
			Constants.searchLog.debug(reLog);
			break;
		}
		case ReportMsgType.INFO_APP_USE:
		{
			AppUserInfo msg = GjsonUtil.fromJson(msgg, AppUserInfo.class);
			String reLog = msg.toLogString();
			Constants.appUseLog.debug(reLog);
			break;
		}
		case ReportMsgType.INFO_NET_STEP:
		{
			NetMsg msg = GjsonUtil.fromJson(msgg, NetMsg.class);
			String reLog = msg.toLogString();
			Constants.netLog.debug(reLog);
			break;
		}
		case ReportMsgType.INFO_FAV_CHANNEL:
		{
			FavChannelMsg msg = GjsonUtil.fromJson(msgg, FavChannelMsg.class);
			String reLog = msg.toLogString();
			Constants.favChannelLog.debug(reLog);
			break;
		}
		case ReportMsgType.INFO_ORDER_PROGRAM:
		{
			OrderProgramMsg msg = GjsonUtil.fromJson(msgg, OrderProgramMsg.class);
			String reLog = msg.toLogString();
			Constants.orderProgramLog.debug(reLog);
			break;
		}
		case ReportMsgType.INFO_NAVIGAT_SETP:
		{
			NavigatStepMsg msg = GjsonUtil.fromJson(msgg, NavigatStepMsg.class);
			String reLog = msg.toLogString();
			Constants.navigatStepLog.debug(reLog);
			break;
		}
		case ReportMsgType.ERROR_VOD_PLAY:
		{
			VODPlayErrorMsg msg = GjsonUtil.fromJson(msgg, VODPlayErrorMsg.class);
			String reLog = msg.toLogString();
			Constants.vodPlayErrorLog.debug(reLog);
			break;
		}
		case ReportMsgType.ERROR_TV_PLAY:
		{
			TVPlayErrorMsg msg = GjsonUtil.fromJson(msgg, TVPlayErrorMsg.class);
			String reLog = msg.toLogString();
			Constants.tvPlayErrorLog.debug(reLog);
			break;
		}
		case ReportMsgType.ERROR_NET_STEP:
		{
			NetErrorMsg msg = GjsonUtil.fromJson(msgg, NetErrorMsg.class);
			String reLog = msg.toLogString();
			Constants.netErrorLog.debug(reLog);
			break;
		}
		case ReportMsgType.ERROR_APP_EXCEPTION:
		{
			AppExceptionMsg msg = GjsonUtil.fromJson(msgg, AppExceptionMsg.class);
			String reLog = msg.toLogString();
			Constants.appExceptionLog.debug(reLog);
			break;
		}
		case ReportMsgType.INFO_DEVICE_STATU:
		{
			/*
			 * DeviceStatusInfo msg=GjsonUtil.fromJson(msgg,
			 * DeviceStatusInfo.class); String reLog=msg.toLogString();
			 */
			Constants.stbStatusLog.debug(msgg);
			break;
		}
		// 指令执行结果上报，先存在队列里
		case RemoteOrderType.UPGRADE_APP_REPLY:
		{
			AppUpgradeReply re = GjsonUtil.fromJson2(msgg, AppUpgradeReply.class);
			if (upMap.get(re.getRequestId()) != null)
			{
				if (upMap.get(re.getRequestId()).get(re.getMac()) != null)
				{
					UpMessage upmsg = upMap.get(re.getRequestId()).get(re.getMac());
					upmsg.setStatus(Constants.SENDACK);
					if (upmsg.getObj() != null)
					{
						AppUpgradeReply re1 = GjsonUtil.fromJson2(msgg, AppUpgradeReply.class);
						re1.getAppInfoList().addAll(re.getAppInfoList());
						String jsonMsg = GjsonUtil.toJson2(re1);
						upmsg.setObj(jsonMsg);
					} else
					{
						upmsg.setObj(msgg);
					}
					upMap.get(re.getRequestId()).put(re.getMac(), upmsg);
				}
			}
			break;
		}
		case RemoteOrderType.UNINSTALL_APP_REPLY:
		{
			AppUninstallReply re = GjsonUtil.fromJson2(msgg, AppUninstallReply.class);
			if (upMap.get(re.getRequestId()) != null)
			{
				if (upMap.get(re.getRequestId()).get(re.getMac()) != null)
				{
					UpMessage upmsg = upMap.get(re.getRequestId()).get(re.getMac());
					upmsg.setObj(msgg);
					upmsg.setStatus(Constants.SENDACK);
					upMap.get(re.getRequestId()).put(re.getMac(), upmsg);
				}
			}
			break;
		}
		case RemoteOrderType.UPGRADE_ROM_REPLY:
		{
			SystemUpgradeReply re = GjsonUtil.fromJson2(msgg, SystemUpgradeReply.class);
			if (upMap.get(re.getRequestId()) != null)
			{
				if (upMap.get(re.getRequestId()).get(re.getMac()) != null)
				{
					UpMessage upmsg = upMap.get(re.getRequestId()).get(re.getMac());
					upmsg.setObj(msgg);
					upmsg.setStatus(Constants.SENDACK);
					upMap.get(re.getRequestId()).put(re.getMac(), upmsg);
				}
			}
			break;
		}
		case RemoteOrderType.INFO_SCREESHOT_REPLY:
		{
			String user = ctx.channel().attr(USER).get();
			ScreenshotReplay re = GjsonUtil.fromJson2(msgg, ScreenshotReplay.class);
			if (upMap.get(re.getRequestId()) != null)
			{
				if (upMap.get(re.getRequestId()).get(user) != null)
				{
					UpMessage upmsg = upMap.get(re.getRequestId()).get(user);
					upmsg.setObj(msgg);
					upmsg.setStatus(Constants.SENDACK);
					upMap.get(re.getRequestId()).put(re.getMac(), upmsg);
				}
			}
			break;
		}
		case RemoteOrderType.SYSTEM_REBOOT_REPLY:
		{

			SystemRebootReply re = GjsonUtil.fromJson2(msgg, SystemRebootReply.class);
			if (upMap.get(re.getRequestId()) != null)
			{
				if (upMap.get(re.getRequestId()).get(re.getMac()) != null)
				{
					UpMessage upmsg = upMap.get(re.getRequestId()).get(re.getMac());
					upmsg.setObj(msgg);
					upmsg.setStatus(Constants.SENDACK);
					upMap.get(re.getRequestId()).put(re.getMac(), upmsg);
				}
			}
			break;
		}
		}
		// long timee = System.currentTimeMillis();
		// log.debug(orderType+",use time:"+(timee-times));

		// long time1 = System.currentTimeMillis();
		// log.debug("Publish message handle use time:"+(time1-time));
		if (message.fixedHeader().qosLevel() == MqttQoS.AT_LEAST_ONCE)
		{
			MqttMessageIdVariableHeader header = MqttMessageIdVariableHeader.from(msgId);
			MqttPubAckMessage puback = new MqttPubAckMessage(Constants.PUBACK_HEADER, header);
			ctx.write(puback);
		}
		msgg = null;
		topicName = null;
	}


	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx)
	{
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		cause.printStackTrace();
		ctx.close();
	}

	public static Map<String, List<String>> getAreaMap()
	{
		return areaMap;
	}

	public static void setAreaMap(Map<String, List<String>> areaMap)
	{
		MQTTServerHandler.areaMap = areaMap;
	}

	public static Map<String, ChannelHandlerContext> getUserMap()
	{
		return userMap;
	}

	public static void setUserMap(Map<String, ChannelHandlerContext> userMap)
	{
		MQTTServerHandler.userMap = userMap;
	}

	public static Map<ChannelHandlerContext, String> getManagerMap()
	{
		return managerMap;
	}

	public static void setManagerMap(Map<ChannelHandlerContext, String> managerMap)
	{
		MQTTServerHandler.managerMap = managerMap;
	}

	// public static Map<String, Long> getPowerOnMap()
	// {
	// return powerOnMap;
	// }
	//
	// public static void setPowerOnMap(Map<String, Long> powerOnMap)
	// {
	// MQTTServerHandler.powerOnMap = powerOnMap;
	// }
	public static Map<Integer, Map<String, UpMessage>> getUpMap()
	{
		return upMap;
	}

	public static void setUpMap(Map<Integer, Map<String, UpMessage>> upMap)
	{
		MQTTServerHandler.upMap = upMap;
	}

	class SystemMessageThread extends Thread
	{
		String topic;
		String requestXml;

		public SystemMessageThread(String topic, String requestXml)
		{
			this.topic = topic;
			this.requestXml = requestXml;
		}

		@Override
		public void run()
		{
			// long time3 = System.currentTimeMillis();
			String upgradeHost = SystemProperties.getProperty("upgrade.ip");
			String upgradePath = SystemProperties.getProperty("upgrade.path");
			if (Constants.TOPIC_PLAN.equals(topic))
			{
				PlanPac pac = GjsonUtil.fromJson2(requestXml, PlanPac.class);
				if (pac.getPacList() != null && pac.getPacList().size() > 0)
				{
					Integer requestId;
					for (PlanMac plan : pac.getPacList())
					{
						if (plan.getUpgradeOrderList() != null && plan.getUpgradeOrderList().size() > 0)
						{
							// mac为空，区域投放指令
							if (plan.getMacs() == null)
							{
								for (UpgradeOrder order : plan.getUpgradeOrderList())
								{
									// rom升级
									if (order.getMsgCode() == RemoteOrderType.UPGRADE_ROM)
									{
										requestId = getRequestId();
										SystemUpgradeOrder sysOrder = new SystemUpgradeOrder();
										sysOrder.setVersionCode(order.getVersionCode());
										sysOrder.setVersionName(order.getVersionName());
										sysOrder.setUrl(upgradeHost + upgradePath + order.getUrl());
										sysOrder.setMd5(order.getMd5());
										sysOrder.setSupportVersionList(order.getSupportVersionList());
										sysOrder.setMsgCode(order.getMsgCode());
										sysOrder.setPlanId(order.getPlanId());
										sysOrder.setMsgExplain(order.getMsgExplain());
										sysOrder.setRequestId(requestId);
										String[] areads = order.getAreaCode().split(",");
										if (areads != null)
										{
											for (int i = 0; i < areads.length; i++)
											{
												sendMessage(areads[i], sysOrder.getMsgCode(), Constants.AREA_OP, sysOrder.getPlanId(), GjsonUtil.toJson2(sysOrder), requestId);
											}
										}
										Long delay = Long.valueOf(SystemProperties.getProperty("SystemUpgradeReplyDelay", "240"));
										scheduledExecutorService.schedule(new UpSystemMessageThread(requestId), delay, TimeUnit.MINUTES);
										// app升级
									} else if (order.getMsgCode() == RemoteOrderType.UPGRADE_APP || order.getMsgCode() == RemoteOrderType.UNINSTALL_APP)
									{
										requestId = getRequestId();
										AppUpgradeOrder appOrder = new AppUpgradeOrder();
										appOrder.setUpdateType(order.getUpdateType());
										appOrder.setMsgCode(order.getMsgCode());
										appOrder.setMsgExplain(order.getMsgExplain());
										appOrder.setRequestId(requestId);
										List<APPInfo> appList = order.getAppInfoList();
										if (appList != null && appList.size() > 0)
										{
											for (int i = 0; i < appList.size(); i++)
											{
												appList.get(i).setUrl(upgradeHost + upgradePath + appList.get(i).getUrl());
											}
										}
										appOrder.setAppInfoList(appList);
										String[] areads = order.getAreaCode().split(",");
										if (areads != null)
										{
											for (int i = 0; i < areads.length; i++)
											{
												sendMessage(areads[i], appOrder.getMsgCode(), Constants.AREA_OP, appOrder.getPlanId(), GjsonUtil.toJson2(appOrder), requestId);
											}
										}
										Long delay = Long.valueOf(SystemProperties.getProperty("AppUpgradeReplyDelay", "240"));
										scheduledExecutorService.schedule(new UpSystemMessageThread(requestId), delay, TimeUnit.MINUTES);
									}
								}
								// 按mac地址
							} else
							{
								for (UpgradeOrder order : plan.getUpgradeOrderList())
								{
									// rom升级
									if (order.getMsgCode() == RemoteOrderType.UPGRADE_ROM)
									{
										requestId = getRequestId();
										SystemUpgradeOrder sysOrder = new SystemUpgradeOrder();
										sysOrder.setVersionCode(order.getVersionCode());
										sysOrder.setVersionName(order.getVersionName());
										sysOrder.setUrl(upgradeHost + upgradePath + order.getUrl());
										sysOrder.setMd5(order.getMd5());
										sysOrder.setSupportVersionList(order.getSupportVersionList());
										sysOrder.setMsgCode(order.getMsgCode());
										sysOrder.setPlanId(order.getPlanId());
										sysOrder.setMsgExplain(order.getMsgExplain());
										sysOrder.setRequestId(requestId);
										if (plan.getMacs().size() > 0)
										{
											for (String mac : plan.getMacs())
											{
												System.out.println("ddd" + GjsonUtil.toJson(sysOrder));
												sendMessage(mac, sysOrder.getMsgCode(), Constants.MAC_OP, sysOrder.getPlanId(), GjsonUtil.toJson2(sysOrder), requestId);
											}
										}
										Long delay = Long.valueOf(SystemProperties.getProperty("SystemUpgradeReplyDelay", "240"));
										scheduledExecutorService.schedule(new UpSystemMessageThread(requestId), delay, TimeUnit.MINUTES);
										// app升级,需合并
									} else if (order.getMsgCode() == RemoteOrderType.UPGRADE_APP || order.getMsgCode() == RemoteOrderType.UNINSTALL_APP)
									{
										requestId = getRequestId();
										AppUpgradeOrder appOrder = new AppUpgradeOrder();
										appOrder = new AppUpgradeOrder();
										appOrder.setUpdateType(order.getUpdateType());
										appOrder.setPlanId(order.getPlanId());
										appOrder.setMsgCode(order.getMsgCode());
										appOrder.setMsgExplain(order.getMsgExplain());
										appOrder.setRequestId(requestId);
										List<APPInfo> appList = order.getAppInfoList();
										if (appList != null && appList.size() > 0)
										{
											for (int i = 0; i < appList.size(); i++)
											{
												appList.get(i).setUrl(upgradeHost + upgradePath + appList.get(i).getUrl());
											}
										}
										appOrder.setAppInfoList(appList);
										if (plan.getMacs().size() > 0)
										{
											for (String mac : plan.getMacs())
											{
												sendMessage(mac, appOrder.getMsgCode(), Constants.MAC_OP, appOrder.getPlanId(), GjsonUtil.toJson2(appOrder), requestId);
											}
										}
										appOrder.setAppInfoList(appList);
										appOrder.setAppInfoList(order.getAppInfoList());
										Long delay = Long.valueOf(SystemProperties.getProperty("AppUpgradeReplyDelay", "240"));
										scheduledExecutorService.schedule(new UpSystemMessageThread(appOrder.getRequestId()), delay, TimeUnit.MINUTES);
									}
								}
								/*
								 * //合并后，判断是否有app升级
								 * if(appOrder.getAppInfoList()!=
								 * null&&appOrder.getAppInfoList().size()>0) {
								 * if (plan.getMacs().size()>0) { for (String
								 * mac:plan.getMacs()) { sendMessage(mac,
								 * appOrder.getMsgCode(), Constants.MAC_OP,
								 * appOrder.getPlanId(),
								 * GjsonUtil.toJson2(appOrder),
								 * appOrder.getRequestId()); } Long delay =
								 * Long.valueOf(SystemProperties.getProperty(
								 * "AppUpgradeReplyDelay", "240"));
								 * scheduledExecutorService.schedule(new
								 * UpSystemMessageThread
								 * (appOrder.getRequestId()), delay,
								 * TimeUnit.MINUTES); } }
								 */
							}
						}
					}
				}
				// long time4 = System.currentTimeMillis();
				// log.debug("send message use time：" + (time4 - time3));
			} else if (Constants.TOPIC_ORDER.equals(topic))
			{
				Integer requestId = getRequestId();
				OrderCommon order = GjsonUtil.fromJson2(requestXml, OrderCommon.class);
				order.setRequestId(requestId);
				if (StringUtils.isNotEmpty(order.getAreaCode()))
				{
					// 发送指令
					sendMessage(order.getAreaCode(), order.getMsgCode(), Constants.AREA_OP, order.getPlanId(), GjsonUtil.toJson2(order), requestId);
				} else if (StringUtils.isNotEmpty(order.getMac()))
				{
					// 发送指令
					sendMessage(order.getMac(), order.getMsgCode(), Constants.MAC_OP, order.getPlanId(), GjsonUtil.toJson2(order), requestId);
				}
				Long delay = Long.valueOf(SystemProperties.getProperty("ScreenshotReplayDelay", "10"));
				// 延时处理，收集上报信息后上报至管理系统
				scheduledExecutorService.schedule(new UpSystemMessageThread(requestId), delay, TimeUnit.SECONDS);
			}
		}

		private Integer getRequestId()
		{
			Integer requestId = requestIdau.getAndIncrement();
			if (requestId == 65535)
			{
				requestIdau.set(1);
			}
			return requestId;
		}

		/**
		 * 下发指令
		 * 
		 * @param code
		 *            mac或者区域码
		 * @param msgCode
		 *            消息类型
		 * @param op
		 *            =0：code为mac地址，op=1 code为areaCode
		 * @param str
		 *            发送消息
		 */
		public void sendMessage(String code, int msgCode, int op, String planId, String str, int requestId)
		{
			int UpmsgCode = msgCode;
			switch (msgCode)
			{
			case RemoteOrderType.INFO_SCREESHOT:
				UpmsgCode = RemoteOrderType.INFO_SCREESHOT_REPLY;
				break;
			case RemoteOrderType.SYSTEM_REBOOT:
				UpmsgCode = RemoteOrderType.SYSTEM_REBOOT_REPLY;
				break;
			case RemoteOrderType.UNINSTALL_APP:
				UpmsgCode = RemoteOrderType.UNINSTALL_APP_REPLY;
				break;
			case RemoteOrderType.UPGRADE_APP:
				UpmsgCode = RemoteOrderType.UPGRADE_APP_REPLY;
				break;
			case RemoteOrderType.UPGRADE_ROM:
				UpmsgCode = RemoteOrderType.UPGRADE_ROM_REPLY;
				break;
			}
			log.debug("order message: " + str);
			log.debug("order mac: " + code);
			if (op == 0)
			{
				ChannelHandlerContext ctxx = (ChannelHandlerContext) userMap.get(code);
				log.debug("mac ChannelHandlerContext: " + ctxx);
				if (ctxx != null && ctxx.channel().isActive())
				{
					try
					{
						UpMessage upmsg = new UpMessage();
						upmsg.setMac(code);
						upmsg.setPlanId(planId);
						upmsg.setMsgCode(UpmsgCode);
						// 初始状态，发送失败，即未发送
						upmsg.setStatus(Constants.SENDFAILED);

						MqttPublishMessage pubMsg;
						pubMsg = buildPublish(str, Constants.TOPIC_STB, requestId);
						if (upMap.get(requestId) != null && upMap.get(requestId).size() > 0)
						{
							Map<String, UpMessage> umap = upMap.get(requestId);
							umap.put(code, upmsg);
							upMap.put(requestId, umap);
						} else
						{
							Map<String, UpMessage> umap = new ConcurrentHashMap<String, UpMessage>();
							umap.put(code, upmsg);
							upMap.put(requestId, umap);
						}
						log.debug("order send:" + str);
						ReferenceCountUtil.retain(pubMsg);
						ctxx.writeAndFlush(pubMsg);
					} catch (Exception e)
					{
						// log.error("PlanId "+planId+" mac "+code+" sendOrder failed Error: "+e);
					}
				} else
				{
					log.debug("mac ChannelHandlerContext: " + code + " is not onLine");
				}
			} else if (op == 1)
			{
				List<String> macList = areaMap.get(code);
				Iterator<String> macIte = macList.iterator();
				while (macIte.hasNext())
				{
					String mac = macIte.next();
					ChannelHandlerContext ctxx = userMap.get(mac);
					if (ctxx != null && ctxx.channel().isActive())
					{
						try
						{
							UpMessage upmsg = new UpMessage();
							upmsg.setMac(mac);
							upmsg.setPlanId(planId);
							upmsg.setMsgCode(UpmsgCode);
							// 初始状态，发送失败，即未发送
							upmsg.setStatus(Constants.SENDFAILED);
							MqttPublishMessage pubMsg;
							pubMsg = buildPublish(str, Constants.TOPIC_STB, requestId);
							// 将bean放入发送map和上报map
							if (upMap.get(requestId).size() > 0)
							{
								Map<String, UpMessage> umap = upMap.get(requestId);
								umap.put(mac, upmsg);
								upMap.put(requestId, umap);
							} else
							{
								Map<String, UpMessage> umap = new ConcurrentHashMap<String, UpMessage>();
								umap.put(mac, upmsg);
								upMap.put(requestId, umap);
							}
							log.debug("order send:" + str);
							ReferenceCountUtil.retain(pubMsg);
							ctxx.writeAndFlush(pubMsg);
						} catch (Exception e)
						{
							// log.error("PlanId "+planId+" mac "+mac+" sendOrder failed Error: "+e);
						}
					} else if ((ctxx != null && !ctxx.channel().isActive()))
					{
						macIte.remove();
						userMap.remove(mac);
					} else if (ctxx == null)
					{
						macIte.remove();
					}
				}
			}
		}
	}

	class UpSystemMessageThread extends Thread
	{
		private Integer requestId;

		public UpSystemMessageThread(Integer requestId)
		{
			this.requestId = requestId;
		}

		@Override
		public void run()
		{
			List<UpMessage> upmessageList = new ArrayList<UpMessage>();
			log.debug("requestId is: " + requestId);
			log.debug("is not have this upMessage: " + upMap.containsKey(requestId));
			if (upMap.containsKey(requestId) && upMap.get(requestId).size() > 0)
			{
				for (Object ob : upMap.get(requestId).keySet())
				{
					log.debug(GjsonUtil.toJson(upMap.get(requestId).get(ob)));
					upmessageList.add(upMap.get(requestId).get(ob));
				}

				if (upmessageList.size() > 0)
				{
				}
			}
		}
	}

	private int getOrderType(String str)
	{
		String tmp = "";
		int result = -1;
		String regex = "(\"" + RemoteOrderType.MSG_CODE + "\"):(\\d{1,})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		try
		{
			if (m.find())
			{
				tmp = m.group(2);
			}
			if (tmp.length() > 0)
			{
				if (tmp.startsWith("\""))
				{
					tmp = tmp.substring(1, tmp.length() - 1);
				}
				result = Integer.parseInt(tmp);
			}
		} catch (Exception ex)
		{
			log.error("getOrderType Error:", ex);
		}
		return result;
	}

	/**
	 * 执行xshell命令，调用rsync同步管理系统资源至节点
	 */
	/*
	 * public void ReadCmdLine () { Process process = null; String
	 * command=SystemProperties.getProperty("rsync.command"); List<String>
	 * processList = new ArrayList<String>(); try { process =
	 * Runtime.getRuntime().exec(command); BufferedReader input = new
	 * BufferedReader(new InputStreamReader(process.getInputStream())); String
	 * line = ""; while ((line = input.readLine()) != null) {
	 * processList.add(line); } input.close(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * for (String line : processList) { System.out.println(line); } }
	 */

	public static void main(String[] args)
	{
		// long time = System.currentTimeMillis();
		/*
		 * String key = RemoteOrderType.MSG_CODE; String regex =
		 * "(\"msgCode\"):(\"[^\"]+\"|\\d)"; String test =
		 * "{\"test2\":\"tmp\",\"msgCode\":\"1\",\"test\":\"tmp\"}"; Pattern p =
		 * Pattern.compile(regex); Matcher m = p.matcher(test);
		 * System.out.println(m.toString()); System.out.println(m.matches());
		 * while(m.find()){ String s = m.group(); System.out.println(s);
		 * System.out.println("Group 0:"+m.group(0));
		 * System.out.println("Group 1:"+m.group(1));
		 * System.out.println("Group 2:"+m.group(2)); }
		 * System.out.println(System.currentTimeMillis()-time);
		 */
		OrderCommon co = new OrderCommon();
		co.setMsgCode(ReportMsgType.INFO_APP_USE);
		System.out.println(GjsonUtil.toJson(co));
		// System.out.println(getOrderType(GjsonUtil.toJson(co)));
		
		Constants.tvPlayLog.debug("34632463456334");
		// 频道实时收视率
		Constants.realTimeTam.debug("11111111");
		
	}
}
