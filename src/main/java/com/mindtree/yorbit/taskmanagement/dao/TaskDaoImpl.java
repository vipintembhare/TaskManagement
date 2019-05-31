package com.mindtree.yorbit.taskmanagement.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.mindtree.yorbit.taskmanagement.model.Employee;
import com.mindtree.yorbit.taskmanagement.model.Project;
import com.mindtree.yorbit.taskmanagement.model.Task;
@Component
public class TaskDaoImpl implements TaskDao{

	private static final Logger logger = Logger.getLogger(TaskDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	/* (non-Javadoc)
	 * @see com.mindtree.yorbit.taskmanagement.dao.TaskDao#addTask(com.mindtree.yorbit.taskmanagement.model.Task)
	 */
	@Override
	public void addTask(Task task) {
		logger.info("Adding task");
		Session session= this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(task);
		logger.info("Task saved successfully.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> listTasks() {
		logger.info("Getting list of Tasks");
		Session session=this.sessionFactory.getCurrentSession();
		List<Task> taskList = session.createQuery("from Task").list();
		
			for(Task task : taskList){
				Hibernate.initialize(task.getAssignedResources());
				Hibernate.initialize(task.getProject());
			}
		
		return taskList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> listProjects() {
		logger.info("Getting project list");
		Session session=this.sessionFactory.getCurrentSession();
		List<Project> projectList = session.createQuery("from Project").list();
		if(logger.isDebugEnabled()){
			for(Project project : projectList){
				logger.debug("Task List::"+project.getProjectName());
			}
		}
		return projectList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listEmployees(String projectId) {
		logger.info("Getting employee list");
		Session session=this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Employee e where e.project.projectId =:projectId");
		query.setParameter("projectId", projectId);
		
		List<Employee> employeeList = query.list();
		if(logger.isDebugEnabled()){
			for(Employee employee : employeeList){
				logger.debug("employee List::"+employee);
			}
			
		}
		return employeeList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Project findProjectByProjectId(String projectId) {
		logger.info("Getting project with project id"+projectId);
		Session session=this.sessionFactory.getCurrentSession();
		return (Project) session.get(Project.class, projectId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Employee findEmployeeByEmpId(String empId) {
		logger.info("Getting employee with emp id"+empId);
		Session session=this.sessionFactory.getCurrentSession();
		return (Employee) session.get(Employee.class, empId);
	}

	@Override
	public List<Task> listTasksByProjectId(String projectId) {
		logger.info("Getting tasks for project id"+projectId);
		Session session=this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Task t where t.project.projectId =:projectId");
		query.setParameter("projectId", projectId);
		
		List<Task> tasks = query.list();
		for(Task task : tasks){
			Hibernate.initialize(task.getAssignedResources());
			Hibernate.initialize(task.getProject());
		}
		return tasks;
	}
}
