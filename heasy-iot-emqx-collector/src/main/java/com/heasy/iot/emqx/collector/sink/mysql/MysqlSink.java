package com.heasy.iot.emqx.collector.sink.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.heasy.iot.emqx.collector.sink.AbstractSink;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MysqlSink extends AbstractSink{
	private static final Logger logger = LoggerFactory.getLogger(MysqlSink.class);
	public static final String SINK_MYSQL = "mysql";
	
	@Autowired
	private MysqlSinkDao dao;

	
	public MysqlSink(){
		super(MysqlSink.SINK_MYSQL);
	}
	
	@Override
	public void process(Object object) {
		try{
			if(object instanceof JSONObject){
				JSONObject jsonObject = (JSONObject)object;
				
				MqttEmqxPackage pck = new MqttEmqxPackage();
				pck.setEvent(JsonUtil.getString(jsonObject, "event"));
				pck.setClientid(JsonUtil.getString(jsonObject, "clientid"));
				pck.setUsername(JsonUtil.getString(jsonObject, "username"));
				pck.setNode(JsonUtil.getString(jsonObject, "node"));
				pck.setTimestamp(JsonUtil.getLong(jsonObject, "timestamp"));
	
				pck.setPeername(JsonUtil.getString(jsonObject, "peername"));
				pck.setSockname(JsonUtil.getString(jsonObject, "sockname"));
				pck.setProto_name(JsonUtil.getString(jsonObject, "proto_name"));
				pck.setProto_ver(JsonUtil.getFloat(jsonObject, "proto_ver"));
	
				pck.setMessageid(JsonUtil.getString(jsonObject, "id"));
				pck.setPeerhost(JsonUtil.getString(jsonObject, "peerhost"));
				pck.setPublish_received_at(JsonUtil.getLong(jsonObject, "publish_received_at"));
				pck.setTopic(JsonUtil.getString(jsonObject, "topic"));
				pck.setQos(JsonUtil.getInt(jsonObject, "qos"));
				pck.setPayload(JsonUtil.getString(jsonObject, "payload"));
				pck.setFrom_username(JsonUtil.getString(jsonObject, "from_username"));
				pck.setFrom_clientid(JsonUtil.getString(jsonObject, "from_clientid"));
	
				pck.setDisconnected_at(JsonUtil.getLong(jsonObject, "disconnected_at"));
				pck.setReason(JsonUtil.getString(jsonObject, "reason"));
				
				JSONObject o1 = jsonObject.getJSONObject("conn_props");
				if(o1 != null && !o1.isNullObject() && o1.containsKey("User-Property-Pairs")){
					JSONArray array = o1.getJSONArray("User-Property-Pairs");
					if(array != null){
						for(int i=0; i<array.size(); i++){
							JSONObject o2 = (JSONObject)array.get(i);
							String key = JsonUtil.getString(o2, "key");
							String value = JsonUtil.getString(o2, "value");
							pck.getUser_properties().put(key, value);
						}
					}
				}
				
				logger.debug("解析后的报文：" + JSONObject.fromObject(pck).toString(2));
				dao.insert(pck);
			}
		}catch(Exception ex){
			logger.error("", ex);
		}
	}

	public void setDao(MysqlSinkDao dao) {
		this.dao = dao;
	}
}
