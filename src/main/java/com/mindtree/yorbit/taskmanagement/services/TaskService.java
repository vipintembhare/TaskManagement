package com.mindtree.yorbit.taskmanagement.services;

import java.util.List;

import com.mindtree.yorbit.taskmanagement.model.Employee;
import com.mindtree.yorbit.taskmanagement.model.Project;
import com.mindtree.yorbit.taskmanagement.model.Task;

public interface TaskService {


	public void addTask(Task task);
	public List<Task> listTasks();
	public List<Task> listTasksByProjectId(String projectId);
	public List<Project> listProjects();
	public List<Employee> listEmployees(String projectId);
	Project findProjectByProjectId(String projectId);
	Employee findEmployeeByEmpId( String empId);
	
}
