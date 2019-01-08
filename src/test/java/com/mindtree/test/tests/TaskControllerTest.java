package com.mindtree.test.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mindtree.test.config.TestContext;
import com.mindtree.yorbit.taskmanagement.model.Task;
import com.mindtree.yorbit.taskmanagement.services.TaskService;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:servlet-context.xml"}, classes={TestContext.class})
//@WebAppConfiguration
public class TaskControllerTest {
	
	private MockMvc mockMvc;
	@Autowired
	private TaskService taskServiceMock;
	
	/*@Autowired
	private WebApplicationContext webApplicationContext;
	*/ 
	@Before
	public void setUp() {
	   Mockito.reset(taskServiceMock);
	//   mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	public void ADD(){}
	/*@SuppressWarnings("deprecation")
	@Test
    public void add_NewTaskEntry_ShouldAddTaskEntryAndRenderViewTaskEntryView() throws Exception {
       
 
        when(taskServiceMock.addTask(isA(Task.class))).thenReturn(null);
 
        mockMvc.perform(post("/todo/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "description")
                .param("title", "title")
        )
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:todo/{id}"))
                .andExpect(redirectedUrl("/todo/1"))
                .andExpect(model().attribute("id", is("1")))
                .andExpect(flash().attribute("feedbackMessage", is("Todo entry: title was added.")));
 
        ArgumentCaptor<Task> formObjectArgument = ArgumentCaptor.forClass(Task.class);
        verify(taskServiceMock, times(1)).addTask(formObjectArgument.capture());
        verifyNoMoreInteractions(taskServiceMock);
 
        Task formObject = formObjectArgument.getValue();
 
        assertThat(formObject.getTaskDesc(), is("description"));
        assertNull(formObject.getTaskId());
        assertThat(formObject.getTaskId(), is("title"));
    }
}*/
}
