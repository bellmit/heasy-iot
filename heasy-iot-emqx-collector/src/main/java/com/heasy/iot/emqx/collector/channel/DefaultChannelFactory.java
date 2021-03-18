package com.heasy.iot.emqx.collector.channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.heasy.iot.emqx.collector.utils.StringUtil;

@Component
@PropertySource("classpath:collector.properties")
public class DefaultChannelFactory implements ChannelFactory, InitializingBean, DisposableBean{
	private static final Logger logger = LoggerFactory.getLogger(DefaultChannelFactory.class);
	
	@Value("${channels.scanBasePaths}")
	private String scanBasePaths;
	
    @Value("${channels.default.type}")
    private String channelDefaultType;
	
	private Map<String, Channel> channels = new HashMap<>();
	private ExecutorService service;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("init DefaultChannelFactory...");
		
		service = Executors.newSingleThreadExecutor();
		
		if(StringUtil.isNotEmpty(scanBasePaths)){
			for(String path : scanBasePaths.split(",")){
				//根据接口类加载所有Channel对象
				Set<Class<?extends Channel>> classes = getFullReflections(path).getSubTypesOf(Channel.class);
				if(CollectionUtils.isNotEmpty(classes)){
					for(Class clazz : classes){
						if(clazz.getSimpleName().equalsIgnoreCase("AbstractChannel")){
							continue;
						}
						
						try{
							Channel channel = (Channel)clazz.newInstance();
							if(!channels.containsKey(channel.getName())){
								channel.start();
								channels.put(channel.getName(), channel);
							}
						}catch(Exception ex){
							logger.error("start channel error", ex);
						}
					}
				}
			}
		}
	}
	
	@Override
	public Channel getChannel() {
		return getChannel(channelDefaultType);
	}
	
	@Override
	public Channel getChannel(String name) {
		if(channels.containsKey(name)){
			AbstractChannel channel = (AbstractChannel)channels.get(name);
			
			if(channel.getLifecycleState() != LifecycleState.START){
				service.execute(() -> {
					try{
						logger.error("重启Channel: " + channel.getName() + "...");
						channel.stop();
						channel.start();
					}catch(Exception ex){
						logger.error("restart channel error", ex);
					}
				});
			}else{
				return channel;
			}
		}
		return null;
	}
	
	@Override
	public void destroy() throws Exception {
		logger.info("destroy DefaultChannelFactory...");
		
		if(service != null){
			service.shutdown();
		}
		
		if(channels != null){
			for(Entry<String, Channel> entry : channels.entrySet()){
				entry.getValue().stop();
			}
			channels.clear();
		}
	}
	
	/**
	 * 如果没有配置scanner，默认使用SubTypesScanner和TypeAnnotationsScanner
	 * @param basePackage 包路径
	 */
	private Reflections getFullReflections(String basePackage){
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.addUrls(ClasspathHelper.forPackage(basePackage));
		builder.setScanners(new TypeAnnotationsScanner(), new SubTypesScanner(),
				new MethodAnnotationsScanner(), new FieldAnnotationsScanner());
		builder.filterInputsBy(new FilterBuilder().includePackage(basePackage));
		
		Reflections reflections = new Reflections(builder);
		return reflections;
	}
}
