package com.heasy.iot.emqx.collector.channel;

public interface Channel {
	String getName();
	void start();
	void put(Object data);
	Object take();
	void stop();
}
