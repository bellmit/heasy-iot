package com.heasy.iot.emqx.collector.sink;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:collector.properties")
public class LoggerSinkConfig {
	@Value("${sink.queueCapacity}")
	private int queueCapacity;
	
	@Bean
	@ConditionalOnProperty(name="sink.type", havingValue="logger")
	public LoggerSink createSink(){
		LoggerSink sink = new LoggerSink();
		sink.setQueueCapacity(queueCapacity);
		return sink;
	}
}
