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
	public void doSink(Object pck) {
		logger.info("解析后的报文：\n" + JSONObject.fromObject(pck).toString(2));
		
		if(pck instanceof ClientConnectedPackage){
			dao.insertClientConnectedPackage((ClientConnectedPackage)pck);
		}else if(pck instanceof ClientDisconnectedPackage){
			dao.insertClientDisconnectedPackage((ClientDisconnectedPackage)pck);
		}else if(pck instanceof SessionSubscribedPackage){
			dao.insertSessionSubscribedPackage((SessionSubscribedPackage)pck);
		}else if(pck instanceof SessionUnsubscribedPackage){
			dao.insertSessionUnsubscribedPackage((SessionUnsubscribedPackage)pck);
		}else if(pck instanceof MessageDeliveredPackage){
			dao.insertMessageDeliveredPackage((MessageDeliveredPackage)pck);
		}else if(pck instanceof MessageAckedPackage){
			dao.insertMessageAckedPackage((MessageAckedPackage)pck);
		}else if(pck instanceof MessageDroppedPackage){
			dao.insertMessageDroppedPackage((MessageDroppedPackage)pck);
		}
	}

	public void setDao(MysqlSinkDao dao) {
		this.dao = dao;
	}
}
