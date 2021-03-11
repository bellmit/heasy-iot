package com.heasy.iot.emqx.collector.bean;

public class SessionUnsubscribedPackage extends BasePackage{
	public SessionUnsubscribedPackage(){
		setEvent(BasePackage.EVENT_SESSION_UNSUBSCRIBED);
	}
}
