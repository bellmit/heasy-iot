package com.heasy.iot.emqx.collector.sink.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heasy.iot.emqx.collector.sink.AbstractSink;

import net.sf.json.JSONObject;

public class LoggerSink extends AbstractSink{
	private static final Logger logger = LoggerFactory.getLogger(LoggerSink.class);
	
	@Override
	public void doSink(JSONObject sourcePackage, Object parsedPackage) {
		logger.info("解析后的报文：\n" + JSONObject.fromObject(parsedPackage).toString(4));
	}
}
