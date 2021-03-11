package com.heasy.knowroute.test.controller;

import org.junit.Test;

import com.heasy.iot.emqx.collector.utils.JsonUtil;

public class MqttControllerTest extends BaseTest{
    @Test
    public void collect() throws Exception{
    	String json = JsonUtil.toJSONString("uid", "cjm", "pwd", "123");
    	post("/mqtt/collect", json);
    }
}
