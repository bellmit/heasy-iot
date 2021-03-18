package com.heasy.iot.emqx.collector.sink.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.heasy.iot.emqx.collector.sink.AbstractSink;

public class MongodbSink extends AbstractSink{
	public static final String SINK_MONGODB = "mongodb";
	private String collectionName; //集合名
	
	@Autowired
	MongoTemplate mongoTemplate;

	public MongodbSink(){
		super(MongodbSink.SINK_MONGODB);
	}
	
	@Override
	public void process(Object object) {
		mongoTemplate.insert(object, getCollectionName());
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
