package com.heasy.iot.emqx.collector.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heasy.iot.emqx.collector.utils.JsonUtil;

@RestController
public class MqttController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(MqttController.class);
    
	@RequestMapping(value="/collect", method=RequestMethod.POST)
	public ResponseEntity<String> test(@RequestBody String content){
		String msg = "";
		try {
			msg = JsonUtil.string2object(content).toString(4);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		logger.info("\n" + msg);
		
		return ResponseEntity.ok(null);
	}

}
