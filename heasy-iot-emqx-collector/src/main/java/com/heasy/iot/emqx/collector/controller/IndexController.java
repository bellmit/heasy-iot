package com.heasy.iot.emqx.collector.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heasy.iot.emqx.collector.utils.DatetimeUtil;

@Controller
public class IndexController extends BaseController{
	@RequestMapping("/index")
	@ResponseBody
	public ResponseEntity<String> index(ModelMap modelMap){
		return ResponseEntity.ok(DatetimeUtil.getToday());
	}
}
