package com.heasy.iot.emqx.collector.channel;

public abstract class AbstractChannel implements Channel {
	private String name;
	private LifecycleState lifecycleState;
	
	public AbstractChannel(){
		lifecycleState = LifecycleState.IDLE;
	}

	@Override
	public synchronized void start() {
		lifecycleState = LifecycleState.START;
	}
	
	@Override
	public synchronized void stop() {
		lifecycleState = LifecycleState.STOP;
	}
	
	public synchronized LifecycleState getLifecycleState() {
		return lifecycleState;
	}
	
	public synchronized void setLifecycleState(LifecycleState lifecycleState) {
		this.lifecycleState = lifecycleState;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
