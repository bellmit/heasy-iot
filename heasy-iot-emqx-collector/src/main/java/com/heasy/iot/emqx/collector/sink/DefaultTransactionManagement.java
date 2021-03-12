package com.heasy.iot.emqx.collector.sink;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * 默认事务管理器
 */
@EnableTransactionManagement  //开启注解式事务
@Configuration
public class DefaultTransactionManagement implements TransactionManagementConfigurer{
	@Resource(name="txManager")
    private PlatformTransactionManager txManager;
	
	@Bean(name="txManager")
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager;
	}
}
