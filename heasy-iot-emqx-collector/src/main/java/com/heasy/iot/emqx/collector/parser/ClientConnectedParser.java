package com.heasy.iot.emqx.collector.parser;

import com.heasy.iot.emqx.collector.bean.BasePackage;
import com.heasy.iot.emqx.collector.bean.ClientConnectedPackage;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClientConnectedParser extends AbstractParser<ClientConnectedPackage>{
	@Override
	public ClientConnectedPackage process(JSONObject jsonObject) {
		ClientConnectedPackage pck = new ClientConnectedPackage();
		parseBaseInfo(pck, jsonObject);
		parseConnProperties(pck, jsonObject);
		pck.setPeername(JsonUtil.getString(jsonObject, "peername"));
		pck.setSockname(JsonUtil.getString(jsonObject, "sockname"));
		pck.setProto_name(JsonUtil.getString(jsonObject, "proto_name"));
		pck.setProto_ver(JsonUtil.getFloat(jsonObject, "proto_ver"));
		return pck;
	}

	/**
	 * 提取连接报文的用户属性
	 */
	private void parseConnProperties(BasePackage pck, JSONObject jsonObject) {
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
}
