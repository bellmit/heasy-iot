package com.heasy.iot.emqx.collector.bean;

import com.heasy.iot.emqx.collector.common.EventConstants;

public class SessionUnsubscribedPackage extends BasePackage{
	private String peerhost;
	private String topic;
	private int qos;
	
	public SessionUnsubscribedPackage(){
		setEvent(EventConstants.EVENT_SESSION_UNSUBSCRIBED);
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
