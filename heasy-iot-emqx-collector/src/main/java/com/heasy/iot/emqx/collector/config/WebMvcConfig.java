package com.heasy.iot.emqx.collector.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMVC配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	/**
	 * 设置默认的访问页面
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:/index");
	}
}
