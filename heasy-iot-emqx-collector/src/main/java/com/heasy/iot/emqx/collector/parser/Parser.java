package com.heasy.iot.emqx.collector.parser;

import net.sf.json.JSONObject;

public interface Parser<T> {
	T process(JSONObject jsonObject);
}
