package com.heasy.iot.emqx.collector.parser;

import com.heasy.iot.emqx.collector.bean.SessionSubscribedPackage;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONObject;

public class SessionSubscribedParser extends AbstractParser<SessionSubscribedPackage>{
	@Override
	public SessionSubscribedPackage process(JSONObject jsonObject) {
		SessionSubscribedPackage pck = new SessionSubscribedPackage();
		parseBaseInfo(pck, jsonObject);
		pck.setPeerhost(JsonUtil.getString(jsonObject, "peerhost"));
		pck.setTopic(JsonUtil.getString(jsonObject, "topic"));
		pck.setQos(JsonUtil.getInt(jsonObject, "qos"));
		return pck;
	}
}
