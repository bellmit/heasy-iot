package com.heasy.iot.emqx.collector.parser;

import com.heasy.iot.emqx.collector.bean.MessageAckedPackage;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONObject;

public class MessageAckedParser extends AbstractParser<MessageAckedPackage>{
	@Override
	public MessageAckedPackage process(JSONObject jsonObject) {
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
		return pck;
	}
}
