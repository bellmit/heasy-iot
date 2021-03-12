package com.heasy.iot.emqx.collector.sink.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.heasy.iot.emqx.collector.bean.ClientConnectedPackage;
import com.heasy.iot.emqx.collector.bean.ClientDisconnectedPackage;
import com.heasy.iot.emqx.collector.bean.MessageAckedPackage;
import com.heasy.iot.emqx.collector.bean.MessageDeliveredPackage;
import com.heasy.iot.emqx.collector.bean.MessageDroppedPackage;
import com.heasy.iot.emqx.collector.bean.SessionSubscribedPackage;
import com.heasy.iot.emqx.collector.bean.SessionUnsubscribedPackage;
import com.heasy.iot.emqx.collector.sink.AbstractSink;

import net.sf.json.JSONObject;

public class MysqlSink extends AbstractSink{
	private static final Logger logger = LoggerFactory.getLogger(MysqlSink.class);
	
	@Autowired
	private MysqlSinkDao dao;
	
	@Override
	public void doSink(JSONObject sourcePackage, Object parsedPackage) {
		logger.info("解析后的报文：\n" + JSONObject.fromObject(parsedPackage).toString(2));
		
		if(parsedPackage instanceof ClientConnectedPackage){
			dao.insertClientConnectedPackage((ClientConnectedPackage)parsedPackage);
		}else if(parsedPackage instanceof ClientDisconnectedPackage){
			dao.insertClientDisconnectedPackage((ClientDisconnectedPackage)parsedPackage);
		}else if(parsedPackage instanceof SessionSubscribedPackage){
			dao.insertSessionSubscribedPackage((SessionSubscribedPackage)parsedPackage);
		}else if(parsedPackage instanceof SessionUnsubscribedPackage){
			dao.insertSessionUnsubscribedPackage((SessionUnsubscribedPackage)parsedPackage);
		}else if(parsedPackage instanceof MessageDeliveredPackage){
			dao.insertMessageDeliveredPackage((MessageDeliveredPackage)parsedPackage);
		}else if(parsedPackage instanceof MessageAckedPackage){
			dao.insertMessageAckedPackage((MessageAckedPackage)parsedPackage);
		}else if(parsedPackage instanceof MessageDroppedPackage){
			dao.insertMessageDroppedPackage((MessageDroppedPackage)parsedPackage);
		}else{
			logger.error("Package not support: " + parsedPackage.getClass().getName());
		}
	}

	public void setDao(MysqlSinkDao dao) {
		this.dao = dao;
	}
}
