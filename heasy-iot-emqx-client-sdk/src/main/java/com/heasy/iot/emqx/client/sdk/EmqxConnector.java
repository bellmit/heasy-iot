package com.heasy.iot.emqx.client.sdk;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.client.MqttToken;
import org.eclipse.paho.mqttv5.client.MqttTopic;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.MqttSubscription;

public class EmqxConnector {
	private EmqxConfig config;
	private EmqxCallback callback;
	private MqttClient client;
	
	public EmqxConnector(EmqxConfig config, EmqxCallback callback) {
		Objects.requireNonNull(config);
		Objects.requireNonNull(callback);
		
		this.config = config;
		this.callback = callback;
		this.callback.setMqttConnector(this);
	}
	
	public void connect() {
		boolean isConnected = false;
		while(!isConnected) {
			isConnected = doConnect();
			
			if(!config.isReconnect()){
				break;
			}
		}
	}
	
	private boolean doConnect(){		
		if(client == null || !client.isConnected()) {
			try {
				System.out.println("start connect...");
				client = createMqttClient();
				client.setCallback(this.callback);
				client.connect(getMqttConnectOptions());
			} catch (Exception ex) {
				String errorMsg = ex.toString();
				if(errorMsg.indexOf("ConnectException", 0) >= 0) {
					System.out.println("connect >> " + errorMsg + " >> " + new Date());
				}else{
					ex.printStackTrace();
				}
				
				destroy();
				
				try {
					TimeUnit.SECONDS.sleep(this.config.getReconnectIntervalSeconds() - 2);
				} catch (InterruptedException e) {
					
				}
				
				return false;
			}
		}
		return true;
	}
	
	private MqttConnectionOptions getMqttConnectOptions() throws Exception {
		MqttConnectionOptions options = new MqttConnectionOptions();
		options.setAutomaticReconnect(false);
		//true表示服务器不保留客户端的连接记录，每次连接到服务器都以新的身份连接
		options.setCleanStart(this.config.isCleanStart());
		options.setUserName(this.config.getUsername());
		options.setPassword(this.config.getPassword().getBytes());
		//连接超时时间，单位为秒
		options.setConnectionTimeout(this.config.getConnectionTimeout());
		//会话心跳时间，单位为秒。服务器每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线
		options.setKeepAliveInterval(this.config.getKeepAliveInterval());
		//设置“遗嘱”消息的主题。若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息
		options.setWill(this.config.getWillTopic(), this.config.getWillMessage());

		//附加信息
		if(this.config.getUserProperties() != null && !this.config.getUserProperties().isEmpty()){
			options.setUserProperties(this.config.getUserProperties());
		}
		
		//SSL
		if(this.config.isSslEnabled()){
			options.setSocketFactory(SSLUtil.getSocketFactoryByCert(this.config));
		}
		
		return options;
	}

	private MqttClient createMqttClient() throws MqttException {
		return new MqttClient(this.config.getServerURI(), this.config.getClientId(), 
				new MemoryPersistence());
	}
	
	public MqttMessage createMessage(String payload){
		return createMessage(0, false, payload);
	}
	
	public MqttMessage createMessage(int qos, String payload){
		return createMessage(qos, false, payload);
	}
	
	public MqttMessage createMessage(int qos, boolean retained, String payload){
		MqttMessage message = new MqttMessage();
		message.setQos(qos);
		message.setRetained(retained);
		message.setPayload(payload.getBytes());
		return message;
	}
	
	/**
	 * 订阅主题
	 */
	public void subscribe(String ... topicNames) throws MqttException{
		MqttSubscription[] options = new MqttSubscription[topicNames.length];
		
		for(int i=0; i<topicNames.length; i++){
			//订阅选项
			MqttSubscription option = new MqttSubscription(topicNames[i], 2);
			option.setNoLocal(true);
			option.setRetainAsPublished(true);
			option.setRetainHandling(1);
			options[i] = option;
		}
		
		getClient().subscribe(options);
	}
	
	public boolean publish(String topicName, MqttMessage message){
		boolean success = false;
		try {
			MqttTopic topic = getClient().getTopic(topicName);
			MqttToken token = topic.publish(message);
			token.waitForCompletion();
			
			if(token.getReasonCodes() == null){
				success = true;
			}else{
				for(int i=0; i<token.getReasonCodes().length; i++){
					if(token.getReasonCodes()[i] == 0){
						success = true;
						break;
					}
				}
			}
			
			if(success){
				System.out.println("ok：ratained=" + message.isRetained() + ", payload=" + new String(message.getPayload()));
			}else{
				System.out.println("ReasonCode=" + token.getReasonCodes() + ", ratained=" + message.isRetained() + ", payload=" + new String(message.getPayload()));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return success;
	}
	
	public void destroy() {
		if(client != null) {
			try {
				if(client.isConnected()) {
					client.disconnect();
				}
				
				client.close();
				client = null;
			} catch (MqttException ex) {
				ex.printStackTrace();
			}
		}
	}

	public MqttClient getClient() {
		return client;
	}

	public EmqxConfig getConfig() {
		return config;
	}
	
}
