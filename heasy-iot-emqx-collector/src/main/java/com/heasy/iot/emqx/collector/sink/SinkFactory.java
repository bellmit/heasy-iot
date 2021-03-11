package com.heasy.iot.emqx.collector.sink;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.heasy.iot.emqx.collector.parser.ParserFactory;

@Component
@PropertySource(value="classpath:collector.properties")
public class SinkFactory {
	private static final Logger logger = LoggerFactory.getLogger(SinkFactory.class);

	@Autowired
	private ParserFactory parserFactory;
	
	@Value("${sink.activeSink}")
	private String activeSink;
	
	@Value("${sink.queueCapacity}")
	private int queueCapacity;
	
	private Sink sink = null;
	
	@PostConstruct
	public void init(){
		if(sink == null){ 
			try {
				sink = (Sink)Class.forName(activeSink).newInstance();
				if(queueCapacity > 0){
					sink.setQueueCapacity(queueCapacity);
				}
				sink.setParserFactory(parserFactory);
				sink.init();
			} catch (Exception ex) {
				logger.error("", ex);
			}
		}
	}
	
	public Sink getSink(){
		return sink;
	}
	
	@PreDestroy
	public void destroy(){
		if(sink != null){
			sink.destroy();
		}
	}
}
