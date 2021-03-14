package com.heasy.iot.emqx.client.sdk;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.UserProperty;

public class EmqxConfig {
	private String serverURI = "tcp://www.knowroute.cn:1883";
	private String username = "knowroute";
	private String password = "knowroute@2021";

	private boolean reconnect = true;
	private boolean cleanStart = false;
	private int connectionTimeout = 10;
	private int keepAliveInterval = 8;
	private int reconnectIntervalSeconds = 5;
	private String clientId = StringUtil.getUUIDString();

	private boolean sslEnabled = false;
	private String protocol = "TLSv1.2";
	private String cafile = "doc/certificate/ca.crt";
	private String clientCertFile = "doc/certificate/client.crt";
	private String clientKeyFile = "doc/certificate/client.key";
	private String clientKeyFilePassword = "123456";
	
	private String willTopic = "topic_will";
	private MqttMessage willMessage = new MqttMessage("offline".getBytes(), 2, false, null);
	
	private List<UserProperty> userProperties = new ArrayList<>();
	
	public boolean isReconnect() {
		return reconnect;
	}

	public void setReconnect(boolean reconnect) {
		this.reconnect = reconnect;
	}

	public boolean isCleanStart() {
		return cleanStart;
	}

	public void setCleanStart(boolean cleanStart) {
		this.cleanStart = cleanStart;
	}

	public String getServerURI() {
		return serverURI;
	}

	public void setServerURI(String serverURI) {
		this.serverURI = serverURI;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getConnectionTimeout() {
		return connectionTimeout;
	}
	
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	
	public int getKeepAliveInterval() {
		return keepAliveInterval;
	}
	
	public void setKeepAliveInterval(int keepAliveInterval) {
		this.keepAliveInterval = keepAliveInterval;
	}

	public int getReconnectIntervalSeconds() {
		return reconnectIntervalSeconds;
	}

	public void setReconnectIntervalSeconds(int reconnectIntervalSeconds) {
		this.reconnectIntervalSeconds = reconnectIntervalSeconds;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public boolean isSslEnabled() {
		return sslEnabled;
	}

	public void setSslEnabled(boolean sslEnabled) {
		this.sslEnabled = sslEnabled;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getCafile() {
		return cafile;
	}

	public void setCafile(String cafile) {
		this.cafile = cafile;
	}

	public String getClientCertFile() {
		return clientCertFile;
	}

	public void setClientCertFile(String clientCertFile) {
		this.clientCertFile = clientCertFile;
	}

	public String getClientKeyFilePassword() {
		return clientKeyFilePassword;
	}

	public void setClientKeyFilePassword(String clientKeyFilePassword) {
		this.clientKeyFilePassword = clientKeyFilePassword;
	}

	public String getClientKeyFile() {
		return clientKeyFile;
	}

	public void setClientKeyFile(String clientKeyFile) {
		this.clientKeyFile = clientKeyFile;
	}

	public String getWillTopic() {
		return willTopic;
	}

	public void setWillTopic(String willTopic) {
		this.willTopic = willTopic;
	}

	public MqttMessage getWillMessage() {
		return willMessage;
	}

	public void setWillMessage(MqttMessage willMessage) {
		this.willMessage = willMessage;
	}

	public List<UserProperty> getUserProperties() {
		return userProperties;
	}

	public void setUserProperties(List<UserProperty> userProperties) {
		this.userProperties = userProperties;
	}
}
