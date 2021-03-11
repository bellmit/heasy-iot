package com.heasy.iot.emqx.collector.service;

import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heasy.iot.emqx.collector.bean.BasePackage;
import com.heasy.iot.emqx.collector.bean.ClientConnectedPackage;
import com.heasy.iot.emqx.collector.bean.ClientDisconnectedPackage;
import com.heasy.iot.emqx.collector.bean.MessageAckedPackage;
import com.heasy.iot.emqx.collector.bean.MessageDeliveredPackage;
import com.heasy.iot.emqx.collector.bean.MessageDroppedPackage;
import com.heasy.iot.emqx.collector.bean.SessionSubscribedPackage;
import com.heasy.iot.emqx.collector.bean.SessionUnsubscribedPackage;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 报文数据的解析和分发服务
 */
public abstract class AbstractMqttService implements MqttService{
	private static final Logger logger = LoggerFactory.getLogger(AbstractMqttService.class);
    public static final int DEFAULT_INIT_CAPACITY = 10000;
    
    private LinkedBlockingQueue<JSONObject> queue;
    private HandleThread handleThread;
    
	@PostConstruct
	public void init(){
		queue = new LinkedBlockingQueue<>(DEFAULT_INIT_CAPACITY);
		
		handleThread = new HandleThread();
		handleThread.setDaemon(true);
		handleThread.start();
		
		logger.info("init MqttService");
	}
	
	@Override
	public void handlePackage(JSONObject jsonObject) {
		try {
			queue.put(jsonObject); //堵塞
		} catch (InterruptedException ex) {
			logger.error(ex.toString());
		}
	}

	@PreDestroy
	public void destroy(){
		if(queue != null){
			queue.clear();
			queue = null;
		}
		
		if(handleThread != null){
			handleThread.interrupt();
			handleThread= null;
		}
		
		logger.info("destroy MqttService");
	}
	
	class HandleThread extends Thread{
		@Override
		public void run() {
			while(true){
				try{
					JSONObject jsonObject = queue.take(); //堵塞
					logger.debug("原始报文：\n" + jsonObject.toString(4));
					
					String event = JsonUtil.getString(jsonObject, "event");
					switch (event) {
						case BasePackage.EVENT_CLIENT_CONNECTED:
							parseClientConnectedPackage(jsonObject);
							break;
						case BasePackage.EVENT_CLIENT_DISCONNECTED:
							parseClientDisconnectedPackage(jsonObject);
							break;
						case BasePackage.EVENT_SESSION_SUBSCRIBED:
							parseSessionSubscribedPackage(jsonObject);
							break;
						case BasePackage.EVENT_SESSION_UNSUBSCRIBED:
							parseSessionUnsubscribedPackage(jsonObject);
							break;
						case BasePackage.EVENT_MESSAGE_DELIVERED:
							parseMessageDeliveredPackage(jsonObject);
							break;
						case BasePackage.EVENT_MESSAGE_ACKED:
							parseMessageAckedPackage(jsonObject);
							break;
						case BasePackage.EVENT_MESSAGE_DROPPED:
							parseMessageDroppedPackage(jsonObject);
							break;
						default:
							break;
					}
				}catch(InterruptedException ex){
					break;
				}catch(Exception ex){
					logger.error("", ex);
				}
			}
		}
	}
	
	private void parseClientConnectedPackage(JSONObject jsonObject){
		ClientConnectedPackage pck = new ClientConnectedPackage();
		parseBaseInfo(pck, jsonObject);
		pck.setPeername(JsonUtil.getString(jsonObject, "peername"));
		pck.setSockname(JsonUtil.getString(jsonObject, "sockname"));
		pck.setProto_name(JsonUtil.getString(jsonObject, "proto_name"));
		pck.setProto_ver(JsonUtil.getFloat(jsonObject, "proto_ver"));
		distributePackage(pck);
	}
	
	private void parseClientDisconnectedPackage(JSONObject jsonObject){
		ClientDisconnectedPackage pck = new ClientDisconnectedPackage();
		parseBaseInfo(pck, jsonObject);
		pck.setPeername(JsonUtil.getString(jsonObject, "peername"));
		pck.setSockname(JsonUtil.getString(jsonObject, "sockname"));
		pck.setDisconnected_at(JsonUtil.getLong(jsonObject, "disconnected_at"));
		pck.setReason(JsonUtil.getString(jsonObject, "reason"));
		distributePackage(pck);
	}
	
	private void parseSessionSubscribedPackage(JSONObject jsonObject){
		SessionSubscribedPackage pck = new SessionSubscribedPackage();
		parseBaseInfo(pck, jsonObject);
		pck.setPeerhost(JsonUtil.getString(jsonObject, "peerhost"));
		pck.setTopic(JsonUtil.getString(jsonObject, "topic"));
		pck.setQos(JsonUtil.getInt(jsonObject, "qos"));
		distributePackage(pck);
	}
	
	private void parseSessionUnsubscribedPackage(JSONObject jsonObject){
		SessionUnsubscribedPackage pck = new SessionUnsubscribedPackage();
		parseBaseInfo(pck, jsonObject);
		distributePackage(pck);
	}
	
	private void parseMessageDeliveredPackage(JSONObject jsonObject){
		MessageDeliveredPackage pck = new MessageDeliveredPackage();
		parseBaseInfo(pck, jsonObject);
		pck.setId(JsonUtil.getString(jsonObject, "id"));
		pck.setPeerhost(JsonUtil.getString(jsonObject, "peerhost"));
		pck.setReceived_at(JsonUtil.getLong(jsonObject, "publish_received_at"));
		pck.setTopic(JsonUtil.getString(jsonObject, "topic"));
		pck.setQos(JsonUtil.getInt(jsonObject, "qos"));
		pck.setPayload(JsonUtil.getString(jsonObject, "payload"));
		pck.setFrom_username(JsonUtil.getString(jsonObject, "from_username"));
		pck.setFrom_clientid(JsonUtil.getString(jsonObject, "from_clientid"));
		distributePackage(pck);
	}
	
	private void parseMessageAckedPackage(JSONObject jsonObject){
		MessageAckedPackage pck = new MessageAckedPackage();
		parseBaseInfo(pck, jsonObject);
		pck.setId(JsonUtil.getString(jsonObject, "id"));
		pck.setPeerhost(JsonUtil.getString(jsonObject, "peerhost"));
		pck.setReceived_at(JsonUtil.getLong(jsonObject, "publish_received_at"));
		pck.setTopic(JsonUtil.getString(jsonObject, "topic"));
		pck.setQos(JsonUtil.getInt(jsonObject, "qos"));
		pck.setPayload(JsonUtil.getString(jsonObject, "payload"));
		pck.setFrom_username(JsonUtil.getString(jsonObject, "from_username"));
		pck.setFrom_clientid(JsonUtil.getString(jsonObject, "from_clientid"));
		distributePackage(pck);
	}
	
	private void parseMessageDroppedPackage(JSONObject jsonObject){
		MessageDroppedPackage pck = new MessageDroppedPackage();
		parseBaseInfo(pck, jsonObject);
		pck.setId(JsonUtil.getString(jsonObject, "id"));
		pck.setPeerhost(JsonUtil.getString(jsonObject, "peerhost"));
		pck.setReceived_at(JsonUtil.getLong(jsonObject, "publish_received_at"));
		pck.setTopic(JsonUtil.getString(jsonObject, "topic"));
		pck.setQos(JsonUtil.getInt(jsonObject, "qos"));
		pck.setPayload(JsonUtil.getString(jsonObject, "payload"));
		pck.setReason(JsonUtil.getString(jsonObject, "reason"));
		distributePackage(pck);
	}

	/**
	 * 提取公共信息
	 */
	private void parseBaseInfo(BasePackage pck, JSONObject jsonObject) {
		pck.setClientid(JsonUtil.getString(jsonObject, "clientid"));
		pck.setUsername(JsonUtil.getString(jsonObject, "username"));
		pck.setTimestamp(JsonUtil.getLong(jsonObject, "timestamp"));
		pck.setNode(JsonUtil.getString(jsonObject, "node"));
		parseUserProperties(pck, jsonObject);
	}

	/**
	 * 提取用户属性
	 */
	private void parseUserProperties(BasePackage pck, JSONObject jsonObject) {
		JSONObject object = jsonObject.getJSONObject("conn_props");
		if(object != null && !object.isNullObject() && object.containsKey("User-Property-Pairs")){
			JSONArray array = object.getJSONArray("User-Property-Pairs");
			if(array != null){
				for(int i=0; i<array.size(); i++){
					JSONObject o = (JSONObject)array.get(i);
					String key = JsonUtil.getString(o, "key");
					String value = JsonUtil.getString(o, "value");
					pck.getUserProperties().put(key, value);
				}
			}
		}
	}

	/**
	 * 分发数据包
	 */
	public abstract void distributePackage(Object pck);
	
}
