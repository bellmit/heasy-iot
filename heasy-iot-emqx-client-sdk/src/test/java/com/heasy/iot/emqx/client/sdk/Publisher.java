package com.heasy.iot.emqx.client.sdk;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.paho.mqttv5.common.MqttMessage;

public class Publisher{
	public static void main(String[] args) throws Exception{
		EmqxConfig config = new EmqxConfig();
		config.setUsername("admin");
		config.setPassword("admin@2021");
		config.setReconnect(false);
		
		EmqxCallback callback = new EmqxCallback();
		EmqxConnector publisher = new EmqxConnector(config, callback);
		publisher.connect();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		MqttMessage message = publisher.createMessage(sdf.format(new Date()));
		publisher.publish("user/13798189352", message);
		publisher.destroy();
	}
}
