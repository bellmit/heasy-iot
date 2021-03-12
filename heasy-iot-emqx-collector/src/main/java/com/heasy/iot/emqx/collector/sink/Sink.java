package com.heasy.iot.emqx.collector.sink;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import net.sf.json.JSONObject;

public interface Sink extends InitializingBean, DisposableBean{
    void process(JSONObject jsonObject);
    void setQueueCapacity(int capacity);
}
