package com.heasy.iot.emqx.collector.sink;

import org.springframework.beans.factory.annotation.Value;

public class BaseConfig {
	@Value("${sink.queueCapacity}")
	private int queueCapacity;

	public int getQueueCapacity() {
		return queueCapacity;
	}

	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

}
