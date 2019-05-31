package com.mindtree.yorbit.taskmanagement.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.mindtree.yorbit.taskmanagement.model.Employee;
import com.mindtree.yorbit.taskmanagement.model.Project;
import com.mindtree.yorbit.taskmanagement.model.Task;
import com.mindtree.yorbit.taskmanagement.services.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:servlet-context.xml"})
@WebAppConfiguration
public class TaskControllerTest {
	
	private MockMvc mockMvc;
	
	
	private TaskService taskServiceMock=mock(TaskService.class);
	
	@Autowired
	private TaskController taskController;
	

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
	   Mockito.reset(taskServiceMock);
	   taskController.setTaskService(taskServiceMock);
	  mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
    public void onSubmit() throws Exception {
       
  
        mockMvc.perform(post("/task/addTask")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("task", new Task())
        )
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));
  
 
        ArgumentCaptor<Task> formObjectArgument = ArgumentCaptor.forClass(Task.class);
        verify(taskServiceMock, times(1)).addTask(formObjectArgument.capture());
        verifyNoMoreInteractions(taskServiceMock);
 
      
    }
	
	@Test
	public void listTasks() throws Exception {
		List<Project> projects= Arrays.asList(new Project());
		List<Task> tasks= Arrays.asList(new Task());
		 when(taskServiceMock.listProjects()).thenReturn(projects);
		 when(taskServiceMock.listTasks()).thenReturn(tasks);
		
		mockMvc.perform(get("/task/show")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("task", new Task())
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("tasks"));
		verify(taskServiceMock, times(1)).listProjects();
		verify(taskServiceMock, times(1)).listTasks();
        verifyNoMoreInteractions(taskServiceMock);

	}
	
	@Test
	public void getTasksByProjectId() throws Exception {
		Task task1=new Task();
		task1.setTaskId(1);
		Task task2= new Task();
		task2.setTaskId(2);

		List<Task> tasks= Arrays.asList(task1,task2);
		 when(taskServiceMock.listTasks()).thenReturn(tasks);
		 when(taskServiceMock.listTasksByProjectId(anyString())).thenReturn(Arrays.asList(task1));
		
		 //passing projectId to get details of that project
		 this.mockMvc.perform(get("/show/tasksbyprojectID").accept(MediaType.APPLICATION_JSON).param("projectId", "1"))
		 .andDo(print())
		 .andExpect(status().isOk())
         .andExpect(content().json("[{\"taskId\":1,\"taskDesc\":null,\"project\":null,\"assignedResources\":null,\"startDate\":null,\"endDate\":null}]"));
	//Passing EMPTY projectId in order to get all the projects
		 this.mockMvc.perform(get("/show/tasksbyprojectID").accept(MediaType.APPLICATION_JSON).param("projectId", ""))
		 .andDo(print())
		 .andExpect(status().isOk())
         .andExpect(content().json("[{\"taskId\":1,\"taskDesc\":null,\"project\":null,\"assignedResources\":null,\"startDate\":null,\"endDate\":null},{\"taskId\":2,\"taskDesc\":null,\"project\":null,\"assignedResources\":null,\"startDate\":null,\"endDate\":null}]"));
	 
	}
	
	@Test
    public void getProjectList() throws Exception {
		List<Project> projects= Arrays.asList(new Project());
		List<Task> tasks= Arrays.asList(new Task());
		when(taskServiceMock.listProjects()).thenReturn(projects);
  
        mockMvc.perform(post("/task/assignTask")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("assignTask"));
         
        verify(taskServiceMock, times(1)).listProjects();
        verifyNoMoreInteractions(taskServiceMock);
 
      
    }
	
	@Test
	public void getEmployeeList() throws Exception {
		Employee emp = new Employee();
		emp.setEmpId("1234");
		
		 when(taskServiceMock.listEmployees(anyString())).thenReturn(Arrays.asList(emp));
		
		 //passing projectId to get employees
		 this.mockMvc.perform(get("/assignTasks/getEmployees").accept(MediaType.APPLICATION_JSON).param("projectId", "1"))
		 .andDo(print())
		 .andExpect(status().isOk())
         .andExpect(content().json("[{\"empId\":\"1234\",\"empName\":null,\"designation\":null,\"email\":null,\"manager\":null,\"department\":null,\"dateOfJoining\":null,\"project\":null}]"));
		 
	}

}
