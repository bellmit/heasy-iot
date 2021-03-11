package com.heasy.iot.emqx.collector.bean;

public class SessionSubscribedPackage extends BasePackage{
	private String peerhost; //客户端ip
	private String topic; //主题名
	private int qos; //服务质量等级
	
	public SessionSubscribedPackage(){
		setEvent(BasePackage.EVENT_SESSION_SUBSCRIBED);
	}
	
	public String getPeerhost() {
		return peerhost;
	}
	
	public void setPeerhost(String peerhost) {
		this.peerhost = peerhost;
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
}
