package com.heasy.iot.emqx.collector.bean;

public class ClientDisconnectedPackage extends BasePackage{
	private String peername; //客户端Socket ip:port
	private String sockname; //服务端Socket ip:port
	private long disconnected_at;
	private String reason;
	
	public ClientDisconnectedPackage(){
		setEvent(BasePackage.EVENT_CLIENT_DISCONNECTED);
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
}
