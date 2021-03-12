package com.heasy.iot.emqx.collector.sink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.heasy.iot.emqx.collector.bean.ClientConnectedPackage;

import net.sf.json.JSONObject;

public class MysqlSink extends AbstractSink{
	private static final Logger logger = LoggerFactory.getLogger(MysqlSink.class);
	
	@Autowired
	private MysqlSinkDao dao;
	
	@Override
	public void doSink(Object pck) {
		logger.info("解析后的报文：\n" + JSONObject.fromObject(pck).toString(4));
		if(pck instanceof ClientConnectedPackage){
			dao.insertClientConnectedPackage((ClientConnectedPackage)pck);
		}
	}

	public void setDao(MysqlSinkDao dao) {
		this.dao = dao;
	}
	
}
