package com.heasy.iot.emqx.collector.sink;

import com.heasy.iot.emqx.collector.channel.Channel;

public interface Sink{
	public static final String SINK_LOGGER = "logger";
	public static final String SINK_MYSQL = "mysql";
	public static final String SINK_MONGODB = "mongodb";
	
	String getName();
	Channel getChannel();
	void setChannel(Channel channel);
    void process(Object object);
}
