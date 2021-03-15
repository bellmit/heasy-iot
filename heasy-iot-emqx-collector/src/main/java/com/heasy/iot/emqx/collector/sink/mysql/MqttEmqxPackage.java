package com.heasy.iot.emqx.collector.sink.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MqttEmqxPackage {
	private String event; //事件名
	private String clientid; //客户端ID
	private String username; //用户名
	private String node; //节点名
	private Long timestamp; //时间戳
	
	private String peername; //客户端Socket ip:port
	private String sockname; //服务端Socket ip:port
	private String proto_name; //协议名
	private float proto_ver; //协议版本

	private String messageid;
	private String peerhost;
	private long publish_received_at;
	private String topic;
	private int qos;
	private String payload;
	private String from_username;
	private String from_clientid;

	private long disconnected_at;
	private String reason;

	private Map<String, String> user_properties = new HashMap<>(); //用户属性
	
	private Date create_time;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getPeername() {
		return peername;
	}

	public void setPeername(String peername) {
		this.peername = peername;
	}

	public String getSockname() {
		return sockname;
	}

	public void setSockname(String sockname) {
		this.sockname = sockname;
	}

	public String getProto_name() {
		return proto_name;
	}

	public void setProto_name(String proto_name) {
		this.proto_name = proto_name;
	}

	public float getProto_ver() {
		return proto_ver;
	}

	public void setProto_ver(float proto_ver) {
		this.proto_ver = proto_ver;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getPeerhost() {
		return peerhost;
	}

	public void setPeerhost(String peerhost) {
		this.peerhost = peerhost;
	}

	public long getPublish_received_at() {
		return publish_received_at;
	}

	public void setPublish_received_at(long publish_received_at) {
		this.publish_received_at = publish_received_at;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getQos() {
		return qos;
	}

	public void setQos(int qos) {
		this.qos = qos;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getFrom_username() {
		return from_username;
	}

	public void setFrom_username(String from_username) {
		this.from_username = from_username;
	}

	public String getFrom_clientid() {
		return from_clientid;
	}

	public void setFrom_clientid(String from_clientid) {
		this.from_clientid = from_clientid;
	}

	public long getDisconnected_at() {
		return disconnected_at;
	}

	public void setDisconnected_at(long disconnected_at) {
		this.disconnected_at = disconnected_at;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Map<String, String> getUser_properties() {
		return user_properties;
	}

	public void setUser_properties(Map<String, String> user_properties) {
		this.user_properties = user_properties;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
