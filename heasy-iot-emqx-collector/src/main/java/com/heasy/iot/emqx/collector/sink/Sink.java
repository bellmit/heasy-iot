package com.heasy.iot.emqx.collector.sink;

import com.heasy.iot.emqx.collector.parser.ParserFactory;

import net.sf.json.JSONObject;

public interface Sink {
    void process(JSONObject jsonObject);
    void setQueueCapacity(int capacity);
    void setParserFactory(ParserFactory parserFactory);
    void init();
    void destroy();
}
