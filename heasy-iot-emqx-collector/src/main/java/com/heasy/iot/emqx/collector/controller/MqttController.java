package com.heasy.iot.emqx.collector.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heasy.iot.emqx.collector.channel.Channel;
import com.heasy.iot.emqx.collector.channel.ChannelFactory;
import com.heasy.iot.emqx.collector.utils.JsonUtil;

import net.sf.json.JSONObject;

/**
 * 用于接收EMQX通过WebHook推送过来的报文数据
 */
@RestController
public class MqttController{
    private static final Logger logger = LoggerFactory.getLogger(MqttController.class);
    
    @Autowired
	private ChannelFactory channelFactory;
    
	@RequestMapping(value="/collect", method=RequestMethod.POST)
	public ResponseEntity<String> collect(@RequestBody String content){
		logger.debug(content);
		
		JSONObject jsonObject = null;
		try {
			jsonObject = JsonUtil.string2object(content);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		
		Channel channel = channelFactory.getChannel();
		if(channel != null){
			if(jsonObject != null){
				logger.debug("put data to channel: " + channel.getName());
				channel.put(jsonObject);
			}
		}else{
			logger.error("channel not found!");
		}
		
		return ResponseEntity.ok(null);
	}
    
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public ResponseEntity<String> test(@RequestBody String content){
		logger.debug(content);
		return ResponseEntity.ok(null);
	}
}
