package com.heasy.iot.emqx.collector.parser;

import com.heasy.iot.emqx.collector.bean.BasePackage;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONObject;

public abstract class AbstractParser<T extends BasePackage> implements Parser<T>{
	/**
	 * 提取公共信息
	 */
	public void parseBaseInfo(BasePackage pck, JSONObject jsonObject) {
		pck.setClientid(JsonUtil.getString(jsonObject, "clientid"));
		pck.setUsername(JsonUtil.getString(jsonObject, "username"));
		pck.setTimestamp(JsonUtil.getLong(jsonObject, "timestamp"));
		pck.setNode(JsonUtil.getString(jsonObject, "node"));
	}
}
