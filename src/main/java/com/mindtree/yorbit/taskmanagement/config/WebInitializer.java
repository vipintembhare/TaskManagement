package com.mindtree.yorbit.taskmanagement.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.h2.server.web.WebServlet;

import org.springframework.web.WebApplicationInitializer;

public class WebInitializer implements 	WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) 
	  throws ServletException {
	  ServletRegistration.Dynamic servlet = servletContext
	    .addServlet("h2-console", new WebServlet());
	  servlet.setLoadOnStartup(2);
	  servlet.addMapping("/console/*");
	}
}
