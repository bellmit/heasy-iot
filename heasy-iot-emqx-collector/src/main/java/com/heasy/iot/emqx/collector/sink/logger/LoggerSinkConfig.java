package com.heasy.iot.emqx.collector.sink.logger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.heasy.iot.emqx.collector.sink.BaseConfig;

@Configuration
@PropertySource("classpath:collector.properties")
public class LoggerSinkConfig extends BaseConfig{
	@Bean
	@ConditionalOnProperty(name="sink.type", havingValue="logger")
	public LoggerSink createSink(){
		LoggerSink sink = new LoggerSink();
		sink.setQueueCapacity(getQueueCapacity());
		return sink;
	}
}
