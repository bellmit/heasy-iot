package com.heasy.iot.emqx.collector.parser;

import com.heasy.iot.emqx.collector.bean.ClientDisconnectedPackage;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONObject;

public class ClientDisconnectedParser extends AbstractParser<ClientDisconnectedPackage>{
	@Override
	public ClientDisconnectedPackage process(JSONObject jsonObject) {
		ClientDisconnectedPackage pck = new ClientDisconnectedPackage();
		parseBaseInfo(pck, jsonObject);
		pck.setPeername(JsonUtil.getString(jsonObject, "peername"));
		pck.setSockname(JsonUtil.getString(jsonObject, "sockname"));
		pck.setDisconnected_at(JsonUtil.getLong(jsonObject, "disconnected_at"));
		pck.setReason(JsonUtil.getString(jsonObject, "reason"));
		return pck;
	}
}
