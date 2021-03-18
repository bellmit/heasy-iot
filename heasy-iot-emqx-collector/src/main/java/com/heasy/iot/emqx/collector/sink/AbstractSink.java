package com.heasy.iot.emqx.collector.sink;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.heasy.iot.emqx.collector.channel.Channel;
import com.heasy.iot.emqx.collector.channel.ChannelFactory;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

public abstract class AbstractSink implements Sink, InitializingBean, DisposableBean{
	private static final Logger logger = LoggerFactory.getLogger(AbstractSink.class);

	private String name;
    private SinkRunner sinkRunner;
    private ChannelFactory channelFactory;
    
    public AbstractSink(String name){
    	this.name = name;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
		sinkRunner = new SinkRunner();
		sinkRunner.setDaemon(true);
		sinkRunner.start();
    }
    
	@Override
	public void destroy() throws Exception {
		if(sinkRunner != null){
			sinkRunner.setRunning(false);
			sinkRunner.interrupt();
			sinkRunner= null;
		}
	}
	
	class SinkRunner extends Thread{
		private AtomicBoolean running = new AtomicBoolean(true);
		
		@Override
		public void run() {
			while(running.get()){
				try{
					Channel channel = getChannelFactory().getChannel();
					if(channel != null){
						Object object = channel.take();
						logger.debug("take data：\n" + JsonUtil.object2String(object));
						process(object);
					}else{
						logger.error("channel not found!");
						TimeUnit.SECONDS.sleep(5);
					}
				}catch(InterruptedException ex){
					setRunning(false);
				}catch(Exception ex){
					logger.error("", ex);
				}
			}
		}
		
		public void setRunning(boolean isRunning) {
			this.running.set(isRunning);
		}
	}


	@Override
	public String getName() {
		return name;
	}

	public ChannelFactory getChannelFactory() {
		return channelFactory;
	}

	public void setChannelFactory(ChannelFactory channelFactory) {
		this.channelFactory = channelFactory;
	}
	
}
