package com.heasy.iot.emqx.collector.sink;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:collector.properties")
public class MysqlSinkConfig{
	@Value("${sink.queueCapacity}")
	private int queueCapacity;
	
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
	@ConditionalOnProperty(name="sink.type", havingValue="mysql")
	public MysqlSinkDao mysqlSinkDao(){
		return new MysqlSinkDao();
	}
	
	@Bean
	@ConditionalOnProperty(name="sink.type", havingValue="mysql")
	public MysqlSink createSink(){
		MysqlSink sink = new MysqlSink();
		sink.setQueueCapacity(queueCapacity);
		return sink;
	}
}
