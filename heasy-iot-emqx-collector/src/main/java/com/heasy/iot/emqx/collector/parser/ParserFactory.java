package com.heasy.iot.emqx.collector.parser;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:collector.properties")
@ConfigurationProperties(prefix="parser")
public class ParserFactory {
	private static final Logger logger = LoggerFactory.getLogger(ParserFactory.class);
	
	private Map<String, String> parserNames = new HashMap<>();
	private Map<String, Parser> parserInstances = new HashMap();
	
	public Parser getParser(String parserName){
		Parser parser = null;
		if(parserInstances.containsKey(parserName)){
			parser = parserInstances.get(parserName);
		}else{
			if(parserNames.containsKey(parserName)){
				String className = parserNames.get(parserName);
				try {
					parser = (Parser)Class.forName(className).newInstance();
				} catch (Exception ex) {
					logger.error("", ex);
				}
			}
		}
		return parser;
	}

	/**
	 * 属性文件参数转Map对象需要提供set方法
	 */
	public void setParserNames(Map<String, String> parserNames) {
		this.parserNames = parserNames;
	}

	public String convertToParserName(String eventName){
		return eventName.replaceAll("\\.", "_");
	}
}
