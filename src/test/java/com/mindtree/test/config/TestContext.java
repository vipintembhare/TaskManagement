package com.mindtree.test.config;

import static org.mockito.Mockito.mock;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.mindtree.yorbit.taskmanagement.controller.TaskController;
import com.mindtree.yorbit.taskmanagement.dao.TaskDao;
import com.mindtree.yorbit.taskmanagement.services.TaskService;
import com.mindtree.yorbit.taskmanagement.services.TaskServiceImpl;

@Configuration
public class TestContext {

 
    @Bean
    public TaskService todoService() {
        return mock(TaskServiceImpl.class);
    }
    
    @Bean
    public TaskController taskController() {
    	return new TaskController();
    }
    
    @Bean
    public TaskDao taskDao() {
    	return mock(TaskDao.class);
    }
}
