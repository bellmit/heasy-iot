package com.heasy.iot.emqx.collector.bean;

import com.heasy.iot.emqx.collector.common.EventConstants;

public class SessionUnsubscribedPackage extends BasePackage{
	public SessionUnsubscribedPackage(){
		setEvent(EventConstants.EVENT_SESSION_UNSUBSCRIBED);
	}
}
