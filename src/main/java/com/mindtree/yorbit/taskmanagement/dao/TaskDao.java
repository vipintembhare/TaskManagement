package com.mindtree.yorbit.taskmanagement.dao;

import java.util.List;

import com.mindtree.yorbit.taskmanagement.model.*;
public interface TaskDao {

	public void addTask(Task task);
	public List<Task> listTasks();
	public List<Task> listTasksByProjectId(String projectId);
	public List<Project> listProjects();
	public List<Employee> listEmployees(String projectId);
	Project findProjectByProjectId(String projectId);
	Employee findEmployeeByEmpId(String empId);

}
