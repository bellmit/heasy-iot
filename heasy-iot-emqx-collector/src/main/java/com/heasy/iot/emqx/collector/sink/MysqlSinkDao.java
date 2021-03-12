package com.heasy.iot.emqx.collector.sink;

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
		
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertMessageDeliveredPackage(MessageDeliveredPackage pck){
		
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertMessageAckedPackage(MessageAckedPackage pck){
		
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertMessageDroppedPackage(MessageDroppedPackage pck){
		
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertSessionSubscribedPackage(SessionSubscribedPackage pck){
		
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insertSessionUnsubscribedPackage(SessionUnsubscribedPackage pck){
		
	}
}
