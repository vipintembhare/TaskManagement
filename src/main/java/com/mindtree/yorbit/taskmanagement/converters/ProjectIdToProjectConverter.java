package com.mindtree.yorbit.taskmanagement.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.mindtree.yorbit.taskmanagement.dao.TaskDao;
import com.mindtree.yorbit.taskmanagement.model.Project;

public class ProjectIdToProjectConverter implements Converter<String, Project>{

	@Autowired
	TaskDao taskDao;
	@Override
	public Project convert(String projectId) {
		return taskDao.findProjectByProjectId(projectId);
	}

	
}
