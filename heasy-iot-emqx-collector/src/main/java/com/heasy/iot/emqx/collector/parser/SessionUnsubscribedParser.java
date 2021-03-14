package com.heasy.iot.emqx.collector.parser;

import com.heasy.iot.emqx.collector.bean.SessionUnsubscribedPackage;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONObject;

public class SessionUnsubscribedParser extends AbstractParser<SessionUnsubscribedPackage>{
	@Override
	public SessionUnsubscribedPackage process(JSONObject jsonObject) {
		SessionUnsubscribedPackage pck = new SessionUnsubscribedPackage();
		parseBaseInfo(pck, jsonObject);
		pck.setPeerhost(JsonUtil.getString(jsonObject, "peerhost"));
		pck.setTopic(JsonUtil.getString(jsonObject, "topic"));
		pck.setQos(JsonUtil.getInt(jsonObject, "qos"));
		return pck;
	}
}
