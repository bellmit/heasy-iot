package com.heasy.iot.emqx.collector.sink.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.heasy.iot.emqx.collector.sink.BaseConfig;

@Configuration
@PropertySource("classpath:collector.properties")
public class MongodbSinkConfig extends BaseConfig{
	private static final String SINK_MONGODB = "mongodb";
	
	@Value("${sink.mongodb.collectionName}")
	private String collectionName;
	
	@Bean
	@ConditionalOnProperty(name="sink.type", havingValue=SINK_MONGODB)
	public MongodbSink createSink(){
		MongodbSink sink = new MongodbSink();
		sink.setQueueCapacity(getQueueCapacity());
		sink.setCollectionName(collectionName);
		return sink;
	}
}
