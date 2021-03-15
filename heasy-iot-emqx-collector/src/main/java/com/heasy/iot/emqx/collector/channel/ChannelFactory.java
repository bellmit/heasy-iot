package com.heasy.iot.emqx.collector.channel;

public interface ChannelFactory{
	Channel getChannel();
	Channel getChannel(String name);
}
