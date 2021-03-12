package com.heasy.iot.emqx.collector.bean;

import com.heasy.iot.emqx.collector.common.EventConstants;

public class MessageDroppedPackage extends BasePackage{
	private String id;
	private String peerhost;
	private long publish_received_at;
	private String topic;
	private int qos;
	private String payload;
	private String reason;
	
	public MessageDroppedPackage(){
		setEvent(EventConstants.EVENT_MESSAGE_DROPPED);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPeerhost() {
		return peerhost;
	}
	
	public void setPeerhost(String peerhost) {
		this.peerhost = peerhost;
	}
	
	public long getPublish_received_at() {
		return publish_received_at;
	}
	
	public void setPublish_received_at(long publish_received_at) {
		this.publish_received_at = publish_received_at;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public int getQos() {
		return qos;
	}
	
	public void setQos(int qos) {
		this.qos = qos;
	}
	
	public String getPayload() {
		return payload;
	}
	
	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
