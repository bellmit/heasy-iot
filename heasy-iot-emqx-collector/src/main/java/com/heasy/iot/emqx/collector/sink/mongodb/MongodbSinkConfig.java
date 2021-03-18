package com.heasy.iot.emqx.collector.sink.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.heasy.iot.emqx.collector.channel.ChannelFactory;

@Configuration
@PropertySource("classpath:collector.properties")
public class MongodbSinkConfig{
    private static final Logger logger = LoggerFactory.getLogger(MongodbSinkConfig.class);
    
	@Value("${sink.mongodb.collectionName}")
	private String collectionName;
	
	@Bean
	@ConditionalOnProperty(name="sink.type", havingValue=MongodbSink.SINK_MONGODB)
	public MongodbSink createSink(ChannelFactory channelFactory){
		logger.debug("create MongodbSink: " + channelFactory);
		MongodbSink sink = new MongodbSink();
		sink.setCollectionName(collectionName);
		sink.setChannelFactory(channelFactory);
		return sink;
	}
}
