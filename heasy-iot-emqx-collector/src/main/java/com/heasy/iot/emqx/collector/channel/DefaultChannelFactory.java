package com.heasy.iot.emqx.collector.channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("init DefaultChannelFactory...");
		
		if(StringUtil.isNotEmpty(scanBasePaths)){
			for(String path : scanBasePaths.split(",")){
				Set<Class<?extends Channel>> classes = getFullReflections(path).getSubTypesOf(Channel.class);
				if(CollectionUtils.isNotEmpty(classes)){
					for(Class clazz : classes){
						if(clazz.getSimpleName().equalsIgnoreCase("AbstractChannel")){
							continue;
						}
						
						Channel channel = (Channel)clazz.newInstance();
						if(!channels.containsKey(channel.getName())){
							channel.start();
							channels.put(channel.getName(), channel);
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
			return channels.get(name);
		}
		return null;
	}
	
	@Override
	public void destroy() throws Exception {
		logger.info("destroy DefaultChannelFactory...");
		
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
