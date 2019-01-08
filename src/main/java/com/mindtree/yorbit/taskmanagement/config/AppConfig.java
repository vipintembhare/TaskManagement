package com.mindtree.yorbit.taskmanagement.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mindtree.yorbit.taskmanagement.converters.TaskConverter;

@EnableWebMvc
@ComponentScan
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
	      converters.add(new TaskConverter());
	  }
}
