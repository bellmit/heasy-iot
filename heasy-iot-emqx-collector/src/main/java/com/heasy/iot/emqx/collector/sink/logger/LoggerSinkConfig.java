package com.heasy.iot.emqx.collector.sink.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.heasy.iot.emqx.collector.channel.ChannelFactory;

@Configuration
@PropertySource("classpath:collector.properties")
public class LoggerSinkConfig{
    private static final Logger logger = LoggerFactory.getLogger(LoggerSinkConfig.class);
    
	@Bean
	@ConditionalOnProperty(name="sink.type", havingValue=LoggerSink.SINK_LOGGER)
	public LoggerSink createSink(ChannelFactory channelFactory){
		logger.debug("create LoggerSink: " + channelFactory);
		LoggerSink sink = new LoggerSink();
		sink.setChannelFactory(channelFactory);
		return sink;
	}
}
