package com.heasy.iot.emqx.collector.parser;

import com.heasy.iot.emqx.collector.bean.MessageDroppedPackage;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONObject;

public class MessageDroppedParser extends AbstractParser<MessageDroppedPackage>{
	@Override
	public MessageDroppedPackage process(JSONObject jsonObject) {
		MessageDroppedPackage pck = new MessageDroppedPackage();
		parseBaseInfo(pck, jsonObject);
		pck.setMessageid(JsonUtil.getString(jsonObject, "id"));
		pck.setPeerhost(JsonUtil.getString(jsonObject, "peerhost"));
		pck.setPublish_received_at(JsonUtil.getLong(jsonObject, "publish_received_at"));
		pck.setTopic(JsonUtil.getString(jsonObject, "topic"));
		pck.setQos(JsonUtil.getInt(jsonObject, "qos"));
		pck.setPayload(JsonUtil.getString(jsonObject, "payload"));
		pck.setReason(JsonUtil.getString(jsonObject, "reason"));
		return pck;
	}
}
