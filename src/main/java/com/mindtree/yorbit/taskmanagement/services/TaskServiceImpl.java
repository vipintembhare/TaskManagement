package com.mindtree.yorbit.taskmanagement.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.yorbit.taskmanagement.dao.TaskDao;
import com.mindtree.yorbit.taskmanagement.model.Employee;
import com.mindtree.yorbit.taskmanagement.model.Project;
import com.mindtree.yorbit.taskmanagement.model.Task;

public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskDao taskDao;
	private static final Logger logger= Logger.getLogger(TaskServiceImpl.class);
	
	public void setTaskDao(TaskDao taskDao){
		this.taskDao=taskDao;
	} 
	
	@Override
	@Transactional
	public void addTask(Task task) {
		logger.info("Adding task");
		this.taskDao.addTask(task);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Task> listTasks() {
		logger.info("Getting task...");
		return this.taskDao.listTasks();
	}

	@Override
	@Transactional
	public List<Project> listProjects() {
		logger.info("Getting projects...");
		return this.taskDao.listProjects();
	}
	
	@Override
	@Transactional
	public List<Employee> listEmployees(String projectId){
		logger.info("Getting employees...");
		return this.taskDao.listEmployees(projectId);
	}
	
	@Override
	@Transactional
	public Project findProjectByProjectId(String projectId) {
		return taskDao.findProjectByProjectId(projectId);
	}

	@Override
	@Transactional
	public Employee findEmployeeByEmpId( String empId) {
		return taskDao.findEmployeeByEmpId(empId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Task> listTasksByProjectId(String projectId) {
		logger.info("Getting tasks by project id...");
		return taskDao.listTasksByProjectId(projectId);
	}
	
	
}
