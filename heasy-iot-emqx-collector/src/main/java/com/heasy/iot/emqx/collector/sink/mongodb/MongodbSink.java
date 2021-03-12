package com.heasy.iot.emqx.collector.sink.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.heasy.iot.emqx.collector.sink.AbstractSink;

import net.sf.json.JSONObject;

public class MongodbSink extends AbstractSink{
	private static final Logger logger = LoggerFactory.getLogger(MongodbSink.class);
	private String collectionName; //集合名
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public void doSink(JSONObject sourcePackage, Object parsedPackage) {
		logger.info("解析后的报文：\n" + JSONObject.fromObject(parsedPackage).toString(2));
		mongoTemplate.insert(parsedPackage, getCollectionName());
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
}
