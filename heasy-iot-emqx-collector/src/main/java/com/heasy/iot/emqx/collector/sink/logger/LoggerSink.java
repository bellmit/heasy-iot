package com.heasy.iot.emqx.collector.sink.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heasy.iot.emqx.collector.sink.AbstractSink;

import net.sf.json.JSONObject;

public class LoggerSink extends AbstractSink{
	private static final Logger logger = LoggerFactory.getLogger(LoggerSink.class);
	public static final String SINK_LOGGER = "logger";
	
	public LoggerSink(){
		super(LoggerSink.SINK_LOGGER);
	}
	
	@Override
	public void process(Object object) {
		logger.info("logger sink process：\n" + JSONObject.fromObject(object).toString(2));
	}
}
