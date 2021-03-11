package com.heasy.iot.emqx.collector.bean;

public class MessageAckedPackage extends BasePackage{
	private String id;
	private String peerhost;
	private long received_at;
	private String topic;
	private int qos;
	private String payload;
	private String from_username;
	private String from_clientid;
	
	public MessageAckedPackage(){
		setEvent(BasePackage.EVENT_MESSAGE_ACKED);
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
	
	public long getReceived_at() {
		return received_at;
	}
	
	public void setReceived_at(long received_at) {
		this.received_at = received_at;
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
	
	public String getFrom_username() {
		return from_username;
	}
	
	public void setFrom_username(String from_username) {
		this.from_username = from_username;
	}
	
	public String getFrom_clientid() {
		return from_clientid;
	}
	
	public void setFrom_clientid(String from_clientid) {
		this.from_clientid = from_clientid;
	}
}
