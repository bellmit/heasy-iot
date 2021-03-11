package com.heasy.iot.emqx.collector.common;

public class EventConstants {
	public static final String EVENT_CLIENT_CONNECTED = "client.connected";
	public static final String EVENT_CLIENT_DISCONNECTED = "client.disconnected";
	public static final String EVENT_SESSION_SUBSCRIBED = "session.subscribed";
	public static final String EVENT_SESSION_UNSUBSCRIBED = "session.unsubscribed";
	public static final String EVENT_MESSAGE_DELIVERED = "message.delivered";
	public static final String EVENT_MESSAGE_ACKED = "message.acked";
	public static final String EVENT_MESSAGE_DROPPED = "message.dropped";
}
