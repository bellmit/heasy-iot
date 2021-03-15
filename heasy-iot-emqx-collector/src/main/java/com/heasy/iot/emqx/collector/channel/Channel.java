package com.heasy.iot.emqx.collector.channel;

public interface Channel {
	public static final String CHANNEL_MEMORY = "memory";
	
	String getName();
	void start();
	void put(Object data);
	Object take();
	void stop();
}
