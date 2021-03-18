package com.heasy.iot.emqx.collector.sink;

import com.heasy.iot.emqx.collector.channel.ChannelFactory;

public interface Sink{
	String getName();
	ChannelFactory getChannelFactory();
	void setChannelFactory(ChannelFactory channelFactory);
    void process(Object object);
}
