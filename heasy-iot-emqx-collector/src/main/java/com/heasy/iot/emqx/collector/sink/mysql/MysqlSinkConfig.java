package com.heasy.iot.emqx.collector.sink.mysql;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.heasy.iot.emqx.collector.sink.BaseConfig;

@Configuration
@PropertySource("classpath:collector.properties")
public class MysqlSinkConfig extends BaseConfig{
	private static final String SINK_MYSQL = "mysql";

	@Bean
	@ConfigurationProperties(prefix="sink.mysql.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
    public JdbcTemplate sqliteJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
	@Bean
	@ConditionalOnProperty(name="sink.type", havingValue=SINK_MYSQL)
	public MysqlSinkDao mysqlSinkDao(){
		return new MysqlSinkDao();
	}
	
	@Bean
	@ConditionalOnProperty(name="sink.type", havingValue=SINK_MYSQL)
	public MysqlSink createSink(){
		MysqlSink sink = new MysqlSink();
		sink.setQueueCapacity(getQueueCapacity());
		return sink;
	}
}
