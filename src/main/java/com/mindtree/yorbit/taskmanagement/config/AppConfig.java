package com.mindtree.yorbit.taskmanagement.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.yorbit.taskmanagement.converters.JsonMapper;
import com.mindtree.yorbit.taskmanagement.converters.TaskConverter;

@EnableWebMvc
@ComponentScan
public class AppConfig extends WebMvcConfigurerAdapter {

	
	 @Override
	    public void configureMessageConverters(
	      List<HttpMessageConverter<?>> converters) {
		 MappingJackson2HttpMessageConverter jacksonMapper= new MappingJackson2HttpMessageConverter();
		 ObjectMapper objectMapper = new JsonMapper();
		 jacksonMapper.setObjectMapper(objectMapper);
		 converters.add(jacksonMapper);
	    }
	 
	@Override
	  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
	      converters.add(new TaskConverter());
	  }
	
	


}
