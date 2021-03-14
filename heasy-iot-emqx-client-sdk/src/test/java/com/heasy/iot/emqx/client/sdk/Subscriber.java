package com.heasy.iot.emqx.client.sdk;

import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;

public class Subscriber{
	public static void main(String[] args) throws MqttException{
		EmqxConfig config = new EmqxConfig();
		config.setClientId("13798189352");
		
		EmqxCallback callback = new KnowrouteEmqxCallback();
		EmqxConnector subscriber = new EmqxConnector(config, callback);
		subscriber.connect();
		subscriber.getClient().subscribe("user/knowroute", 1);
	}
	
	public static class KnowrouteEmqxCallback extends EmqxCallback{
		@Override
		public void messageArrived(String topic, MqttMessage message) throws Exception {
			System.out.println("主题=" + topic + ", messageid=" + message.getId() + ", qos=" + message.getQos() + ", payload=" + new String(message.getPayload()));
		}
	}
}
