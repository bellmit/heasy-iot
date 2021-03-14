package com.heasy.iot.emqx.client.sdk;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;

public class EmqxCallback implements MqttCallback {
	private EmqxConnector mqttConnector;

	public EmqxConnector getMqttConnector() {
		return mqttConnector;
	}

	public void setMqttConnector(EmqxConnector mqttConnector) {
		this.mqttConnector = mqttConnector;
	}
    
	@Override
	public void connectComplete(boolean reconnect, String serverURI) {
		System.out.println("连接成功： username=" + mqttConnector.getConfig().getUsername() + ", clientid=" + mqttConnector.getConfig().getClientId());
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("topic=" + topic + ", messageid=" + message.getId() + ", qos=" + message.getQos() + ", payload=" + new String(message.getPayload()));
	}
	
	@Override
	public void authPacketArrived(int reasonCode, MqttProperties properties) {
		System.out.println("reasonCode=" + reasonCode);
	}
	
	@Override
	public void deliveryComplete(IMqttToken token) {
		
	}
	
	@Override
	public void disconnected(MqttDisconnectResponse disconnectResponse) {
		System.out.println(disconnectResponse.getReturnCode() + ", " + disconnectResponse.getReasonString());
		disconnectResponse.getException().printStackTrace();
		System.out.println("connectionLost >> 连接断开，准备重连：" + disconnectResponse.getException().toString());
		mqttConnector.destroy();
		mqttConnector.connect();
	}
	
	@Override
	public void mqttErrorOccurred(MqttException ex) {
		System.out.println(ex.toString());
	}
	
}
