package com.heasy.iot.emqx.collector.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.heasy.iot.emqx.collector.utils.JsonUtil;

/**
 * 解析后的数据输出到日志文件
 */
@Service
public class DefaultMqttService extends AbstractMqttService{
	private static final Logger logger = LoggerFactory.getLogger(DefaultMqttService.class);

	@Override
	public void distributePackage(Object pck) {
		logger.info("解析后的报文：\n" + JsonUtil.object2String(pck));
	}
}
