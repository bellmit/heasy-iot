package com.heasy.iot.emqx.collector.sink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class LoggerSink extends AbstractSink{
	private static final Logger logger = LoggerFactory.getLogger(LoggerSink.class);
	
	@Override
	public void doSink(Object pck) {
		logger.info("解析后的报文：\n" + JSONObject.fromObject(pck).toString(4));
	}
}
