package com.heasy.iot.emqx.collector.parser;

import com.heasy.iot.emqx.collector.bean.SessionUnsubscribedPackage;

import net.sf.json.JSONObject;

public class SessionUnsubscribedParser extends AbstractParser<SessionUnsubscribedPackage>{
	@Override
	public SessionUnsubscribedPackage process(JSONObject jsonObject) {
		SessionUnsubscribedPackage pck = new SessionUnsubscribedPackage();
		parseBaseInfo(pck, jsonObject);
		return pck;
	}
}
