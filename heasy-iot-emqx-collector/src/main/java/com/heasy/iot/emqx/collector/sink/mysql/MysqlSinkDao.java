package com.heasy.iot.emqx.collector.sink.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.heasy.iot.emqx.collector.bean.ClientConnectedPackage;
import com.heasy.iot.emqx.collector.bean.ClientDisconnectedPackage;
import com.heasy.iot.emqx.collector.bean.MessageAckedPackage;
import com.heasy.iot.emqx.collector.bean.MessageDeliveredPackage;
import com.heasy.iot.emqx.collector.bean.MessageDroppedPackage;
import com.heasy.iot.emqx.collector.bean.SessionSubscribedPackage;
import com.heasy.iot.emqx.collector.bean.SessionUnsubscribedPackage;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

public class MysqlSinkDao {
	@Autowired
    protected JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertClientConnectedPackage(ClientConnectedPackage pck){
		String sql = "insert into client_connected(clientid,username,node,timestamp,peername,sockname,proto_name,proto_ver,user_properties) values (?,?,?,?,?,?,?,?,?)";
    	jdbcTemplate.update(sql, pck.getClientid(), pck.getUsername(), pck.getNode(), pck.getTimestamp(),
    			pck.getPeername(), pck.getSockname(), pck.getProto_name(), pck.getProto_ver(), JsonUtil.object2String(pck.getUserProperties()));
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertClientDisconnectedPackage(ClientDisconnectedPackage pck){
		String sql = "insert into client_disconnected(clientid,username,node,timestamp,peername,sockname,reason,disconnected_at) values (?,?,?,?,?,?,?,?)";
    	jdbcTemplate.update(sql, pck.getClientid(), pck.getUsername(), pck.getNode(), pck.getTimestamp(),
    			pck.getPeername(), pck.getSockname(), pck.getReason(), pck.getDisconnected_at());
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertMessageDeliveredPackage(MessageDeliveredPackage pck){
		String sql = "insert into message_delivered(clientid,username,node,timestamp,peerhost,topic,qos,payload,mid,publish_received_at,from_username,from_clientid) values (?,?,?,?,?,?,?,?,?,?,?,?)";
    	jdbcTemplate.update(sql, pck.getClientid(), pck.getUsername(), pck.getNode(), pck.getTimestamp(),
    			pck.getPeerhost(), pck.getTopic(), pck.getQos(), pck.getPayload(), pck.getId(), 
    			pck.getPublish_received_at(), pck.getFrom_username(), pck.getFrom_clientid());
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertMessageAckedPackage(MessageAckedPackage pck){
		String sql = "insert into message_acked(clientid,username,node,timestamp,peerhost,topic,qos,payload,mid,publish_received_at,from_username,from_clientid) values (?,?,?,?,?,?,?,?,?,?,?,?)";
    	jdbcTemplate.update(sql, pck.getClientid(), pck.getUsername(), pck.getNode(), pck.getTimestamp(),
    			pck.getPeerhost(), pck.getTopic(), pck.getQos(), pck.getPayload(), pck.getId(), 
    			pck.getPublish_received_at(), pck.getFrom_username(), pck.getFrom_clientid());
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertMessageDroppedPackage(MessageDroppedPackage pck){
		String sql = "insert into message_dropped(clientid,username,node,timestamp,peerhost,topic,qos,payload,mid,publish_received_at,reason) values (?,?,?,?,?,?,?,?,?,?,?)";
    	jdbcTemplate.update(sql, pck.getClientid(), pck.getUsername(), pck.getNode(), pck.getTimestamp(),
    			pck.getPeerhost(), pck.getTopic(), pck.getQos(), pck.getPayload(), pck.getId(), 
    			pck.getPublish_received_at(), pck.getReason());
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertSessionSubscribedPackage(SessionSubscribedPackage pck){
		String sql = "insert into session_subscribed(clientid,username,node,timestamp,peerhost,topic,qos) values (?,?,?,?,?,?,?)";
    	jdbcTemplate.update(sql, pck.getClientid(), pck.getUsername(), pck.getNode(), pck.getTimestamp(),
    			pck.getPeerhost(), pck.getTopic(), pck.getQos());
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertSessionUnsubscribedPackage(SessionUnsubscribedPackage pck){
		
	}
}
