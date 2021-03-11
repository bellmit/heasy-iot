package com.heasy.iot.emqx.collector.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heasy.iot.emqx.collector.utils.DatetimeUtil;

@RestController
public class IndexController{
	@RequestMapping("/index")
	public ResponseEntity<String> index(ModelMap modelMap){
		return ResponseEntity.ok(DatetimeUtil.getToday());
	}
}
