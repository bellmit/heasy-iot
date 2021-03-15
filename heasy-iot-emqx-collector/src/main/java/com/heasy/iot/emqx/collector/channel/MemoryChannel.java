package com.heasy.iot.emqx.collector.channel;

import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryChannel extends AbstractChannel {
	private static final Logger logger = LoggerFactory.getLogger(MemoryChannel.class);
	
    private int capacity = 100;
    private LinkedBlockingQueue<Object> queue;
    
    public MemoryChannel(){
    	setName(Channel.CHANNEL_MEMORY);
    }
    
    @Override
    public synchronized void start() {
    	super.start();
		queue = new LinkedBlockingQueue<>(this.capacity);
		logger.info(this.getClass().getSimpleName() + " started!");
    }
    
	@Override
	public void put(Object object) {
		try {
			queue.put(object); //堵塞
		} catch (Exception ex) {
			setLifecycleState(LifecycleState.ERROR);
			logger.error("", ex);
		}
	}
	
	@Override
	public Object take() {
		Object object = null;
		try {
			object = queue.take(); //堵塞
		} catch (Exception ex) {
			setLifecycleState(LifecycleState.ERROR);
			logger.error("", ex);
		}
		return object;
	}
	
	@Override
	public synchronized void stop() {
		super.stop();
		setLifecycleState(LifecycleState.IDLE);
		if(queue != null){
			queue.clear();
			queue = null;
		}
		logger.info(this.getClass().getSimpleName() + " stopped!");
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
		logger.debug("capacity=" + capacity);
	}
}
