package com.heasy.iot.emqx.collector.service;

import net.sf.json.JSONObject;

public interface MqttService {
    void handlePackage(JSONObject jsonObject);
}
