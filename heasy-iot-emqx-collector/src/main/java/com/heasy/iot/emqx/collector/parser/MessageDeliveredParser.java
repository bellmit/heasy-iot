package com.heasy.iot.emqx.collector.parser;

import com.heasy.iot.emqx.collector.bean.MessageDeliveredPackage;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONObject;

public class MessageDeliveredParser extends AbstractParser<MessageDeliveredPackage>{
	@Override
	public MessageDeliveredPackage process(JSONObject jsonObject) {
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
		return pck;
	}
}
