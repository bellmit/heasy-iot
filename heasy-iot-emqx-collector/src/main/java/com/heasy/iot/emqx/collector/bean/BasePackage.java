package com.heasy.iot.emqx.collector.bean;

import java.util.HashMap;
import java.util.Map;

public class BasePackage {
	private String event; //事件名
	private String clientid; //客户端ID
	private String username; //用户名
	private Long timestamp; //时间戳
	private String node; //节点名
	private Map<String, String> userProperties = new HashMap<>(); //用户属性
	
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
	
	public String getClientid() {
		return clientid;
	}
	
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getNode() {
		return node;
	}
	
	public void setNode(String node) {
		this.node = node;
	}
	
	public Map<String, String> getUserProperties() {
		return userProperties;
	}
	
	public void setUserProperties(Map<String, String> userProperties) {
		this.userProperties = userProperties;
	}
}
