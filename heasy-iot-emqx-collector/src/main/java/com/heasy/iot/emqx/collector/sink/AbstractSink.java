package com.heasy.iot.emqx.collector.sink;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.heasy.iot.emqx.collector.parser.Parser;
import com.heasy.iot.emqx.collector.parser.ParserFactory;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONObject;

/**
 * 报文数据的解析和分发服务
 */
public abstract class AbstractSink implements Sink{
	private static final Logger logger = LoggerFactory.getLogger(AbstractSink.class);

	@Autowired
	private ParserFactory parserFactory;
	
    private int capacity = 1000;
    private LinkedBlockingQueue<JSONObject> queue;
    private SinkRunner sinkRunner;
    
    @Override
    public void afterPropertiesSet() throws Exception {
		queue = new LinkedBlockingQueue<>(this.capacity);
		
		sinkRunner = new SinkRunner();
		sinkRunner.setDaemon(true);
		sinkRunner.start();
		
		logger.info("init " + this.getClass().getSimpleName());
    }
    
	@Override
	public void process(JSONObject jsonObject) {
		try {
			queue.put(jsonObject); //堵塞
		} catch (Exception ex) {
			logger.error("", ex);
		}
	}

	@Override
	public void destroy() throws Exception {
		if(queue != null){
			queue.clear();
			queue = null;
		}
		
		if(sinkRunner != null){
			sinkRunner.setRunning(false);
			sinkRunner.interrupt();
			sinkRunner= null;
		}
		
		logger.info("destroy " + this.getClass().getSimpleName());
	}
	
	class SinkRunner extends Thread{
		private AtomicBoolean running = new AtomicBoolean(true);
		
		@Override
		public void run() {
			while(running.get()){
				try{
					JSONObject jsonObject = queue.take(); //堵塞
					logger.debug("原始报文：\n" + jsonObject.toString(4));
					
					String event = JsonUtil.getString(jsonObject, "event");
					
					String parserName = parserFactory.convertToParserName(event);
					logger.debug("parserName=" + parserName);
					
					Parser parser = parserFactory.getParser(parserName);
					if(parser != null){
						Object pck = parser.process(jsonObject);
						doSink(jsonObject, pck);
					}else{
						logger.error("parser not found: " + parserName);
					}
				}catch(InterruptedException ex){
					break;
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
	public void setQueueCapacity(int capacity) {
		this.capacity = capacity;
		logger.debug("capacity=" + capacity);
	}
	
	public abstract void doSink(JSONObject sourcePackage, Object parsedPackage);
	
}
