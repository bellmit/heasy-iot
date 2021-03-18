package com.heasy.iot.emqx.collector.sink.mysql;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.heasy.iot.emqx.collector.channel.ChannelFactory;

@Configuration
@PropertySource("classpath:collector.properties")
public class MysqlSinkConfig{
    private static final Logger logger = LoggerFactory.getLogger(MysqlSinkConfig.class);

	@Bean
	@ConfigurationProperties(prefix="sink.mysql.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
	@Bean
	@ConditionalOnProperty(name="sink.type", havingValue=MysqlSink.SINK_MYSQL)
	public MysqlSink createSink(ChannelFactory channelFactory){
		logger.debug("create MysqlSink: " + channelFactory);
		MysqlSink sink = new MysqlSink();
		sink.setChannelFactory(channelFactory);
		return sink;
	}
	
	@Bean
	@ConditionalOnProperty(name="sink.type", havingValue=MysqlSink.SINK_MYSQL)
	public MysqlSinkDao mysqlSinkDao(){
		return new MysqlSinkDao();
	}
}
