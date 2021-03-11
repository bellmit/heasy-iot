package com.heasy.iot.emqx.collector.bean;

import com.heasy.iot.emqx.collector.common.EventConstants;

public class ClientConnectedPackage extends BasePackage{
	private String peername; //客户端Socket ip:port
	private String sockname; //服务端Socket ip:port
	private String proto_name; //协议名
	private float proto_ver; //协议版本
	
	public ClientConnectedPackage(){
		setEvent(EventConstants.EVENT_CLIENT_CONNECTED);
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
}
