package com.heasy.iot.emqx.collector.sink.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.heasy.iot.emqx.collector.utils.JsonUtil;

public class MysqlSinkDao {
	@Autowired
    protected JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insert(MqttEmqxPackage pck){
		StringBuffer sb = new StringBuffer();
		sb.append("insert into mqtt_emqx");
		sb.append("(event,clientid,username,node,timestamp,");
		sb.append("peername,sockname,proto_name,proto_ver,");
		sb.append("messageid,peerhost,publish_received_at,topic,qos,payload,from_username,from_clientid,");
		sb.append("disconnected_at,reason,user_properties)");
		sb.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    	jdbcTemplate.update(sb.toString(), pck.getEvent(), pck.getClientid(), pck.getUsername(), pck.getNode(), pck.getTimestamp(),
    				pck.getPeername(), pck.getSockname(), pck.getProto_name(), pck.getProto_ver(),
    				pck.getMessageid(), pck.getPeerhost(), pck.getPublish_received_at(), pck.getTopic(), pck.getQos(), pck.getPayload(), pck.getFrom_username(), pck.getFrom_clientid(), 
    				pck.getDisconnected_at(), pck.getReason(), JsonUtil.object2String(pck.getUser_properties()));
	}
}
