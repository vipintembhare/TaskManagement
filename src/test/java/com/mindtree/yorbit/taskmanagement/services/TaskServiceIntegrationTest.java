package com.mindtree.yorbit.taskmanagement.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.yorbit.taskmanagement.dao.TaskDao;
import com.mindtree.yorbit.taskmanagement.model.Employee;
import com.mindtree.yorbit.taskmanagement.model.Project;
import com.mindtree.yorbit.taskmanagement.model.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations={"classpath*:servlet-context.xml"})
public class TaskServiceIntegrationTest {
	
	@Autowired TaskService taskService;
	@Autowired TaskDao taskDao;
	
	/**
	 * Test to add task in DB with TaskDao.
	 */
	@Test
	public void addTask() {
		//initial count of task
		List<Task> tasks=taskDao.listTasks();
		int initialNoOfTasks=tasks.size();
		//Inserting new task
		Task newTask=new Task();
		taskService.addTask(newTask);
		
		//validating listTask
		tasks=taskDao.listTasks();
		assertEquals(initialNoOfTasks+1,tasks.size());
		
	}
	
	/**
	 * Test to list task in DB with TaskDao.
	 */
	@Test
	public void listTasks() {
		List<Task> tasks=taskDao.listTasks();
		assertNotNull(tasks);
		assertNotEquals(0, tasks.size());
	}
	
	/**
	 * Test to list projects in DB with TaskDao.
	 */
	@Test
	public void listProjects() {
		List<Project> projects= taskDao.listProjects();
		assertNotNull(projects);
		assertNotEquals(0, projects.size());
	}

	
	/**
	 * Test to list employees based on project Id in DB with TaskDao.
	 */
	@Test
	public void listEmployees() {
		List<Employee> employee= taskDao.listEmployees("Equifax");
		assertNotNull(employee);
		assertNotEquals(0, employee.size());
	}
	
	/**
	 * Test to get Project based on project Id in DB with TaskDao.
	 */
	@Test
	public void findProjectByProjectId() {
		Project project= taskDao.findProjectByProjectId("Equifax");
		assertNotNull(project);
		assertEquals("Equifax", project.getProjectId());
		}
	
	/**
	 * Negative Test
	 */
	@Test
	public void findProjectByProjectId_Negative() {
		//negative test
		Project project= taskDao.findProjectByProjectId("NOT_A_PROJECT");
		assertNull(project);
	}
	
	
	/**
	 * Test to get employee based on emp Id in DB with TaskDao.
	 */
	@Test
	public void findEmployeeByEmpId() {
		Employee employee= taskDao.findEmployeeByEmpId("M1039991");
		assertNotNull(employee);
		assertEquals("M1039991", employee.getEmpId());
		
	}
	/**
	 * Negative: Test to get employee based on emp Id in DB with TaskDao.
	 */
	@Test
	public void findEmployeeByEmpId_Negative() {
	    //Negative test
		Employee employee= taskDao.findEmployeeByEmpId("NOT_A_PROJECT");
		assertNull(employee);
	}
	
	/**
	 * Negative Test 
	 */
	@Test
	public void listTasksByProjectId_Negative() {
	    //Negative test
		List<Task> tasks= taskDao.listTasksByProjectId("NOT_A_PROJECT");
		assertEquals(true, tasks.isEmpty());
	}
	
	@Test
	public void listTasksByProjectId() {
		List<Task> tasks= taskDao.listTasksByProjectId("Equifax");
		assertEquals(false, tasks.isEmpty());
	}
}
