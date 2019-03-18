package com.mindtree.yorbit.taskmanagement.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mindtree.yorbit.taskmanagement.model.Employee;
import com.mindtree.yorbit.taskmanagement.model.Project;
import com.mindtree.yorbit.taskmanagement.model.Task;
import com.mindtree.yorbit.taskmanagement.services.TaskService;
import com.mindtree.yorbit.taskmanagement.validator.TaskValidator;
@Controller
@RequestMapping("/")
public class TaskController{

	private static final Logger logger = Logger.getLogger(TaskController.class);
	private static final String EMPTY="";
	private TaskService taskService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private TaskValidator taskValidator;
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	    binder.registerCustomEditor(Project.class, "project", new PropertyEditorSupport() {
	        @Override
	        public void setAsText(String projectId) {
	        	Project project = taskService.findProjectByProjectId(projectId);
	            setValue(project);
	        }
	    });
	    
	    binder.registerCustomEditor(Employee.class, "assignedResources", new PropertyEditorSupport() {
	        @Override
	        public void setAsText(String empId) {
	        	Employee employee = taskService.findEmployeeByEmpId(empId);
	            setValue(employee);
	        }
	    });
	    
	    binder.registerCustomEditor(Date.class, "startDate", new PropertyEditorSupport() {
	        @Override
	        public void setAsText(String startDate) {
	        	SimpleDateFormat dateFormatter= new SimpleDateFormat("dd-mm-yyyy");
	        	Date date=null;
				try {
					date = dateFormatter.parse(startDate);
				} catch (ParseException e) {
					
				}
	            setValue(date);
	        }
	    });
	    binder.registerCustomEditor(Date.class, "endDate", new PropertyEditorSupport() {
	        @Override
	        public void setAsText(String endDate) {
	        	SimpleDateFormat dateFormatter= new SimpleDateFormat("dd-mm-yyyy");
	        	Date date=null;
				try {
					date = dateFormatter.parse(endDate);
				} catch (ParseException e) {
					
				}
	            setValue(date);
	        }
	    });
	    
	   // binder.addValidators(taskValidator);
	}
	public void setTaskService(TaskService taskService){
		this.taskService=taskService;
	}
	
	 @RequestMapping("/")
	public String  showMainPage(HttpServletRequest request){
		 logger.info(request.getServletPath());
		 return "index";
	}
	 
	@RequestMapping( value="/task/addTask",method=RequestMethod.POST) 
	public String onSubmit( @ModelAttribute("task") Task task){
			
		this.taskService.addTask(task);
		return createRedirectViewPath("/","redirect:");//"task/assignTask";
	}
	
		
	@RequestMapping(value="/task/show", method=RequestMethod.GET) 
	public ModelAndView listTasks(Model model){
		ModelAndView modelAndView = new ModelAndView("tasks");
		modelAndView.addObject("projectList", taskService.listProjects());
		modelAndView.addObject("tasks", taskService.listTasks());
		modelAndView.addObject("task", new Task());
		return modelAndView;
	}
	
	@RequestMapping(value = "/show/tasksbyprojectID", headers = "Accept=*/*", method = RequestMethod.GET)
    public @ResponseBody
    List<Task> getTasksByProjectId(@RequestParam(value = "projectId", required = true) String projectId)  {
		if(EMPTY.equals(projectId)) {
			return taskService.listTasks();
		}
		return taskService.listTasksByProjectId(projectId);
    }
	
	@ResponseBody
	@RequestMapping(value="/task/assignTask") 
	public ModelAndView getProjectList() {

		List<Project> projectList = taskService.listProjects();
		ModelAndView modelAndView = new ModelAndView("assignTask");
        modelAndView.addObject("projectList", projectList);
        modelAndView.addObject("task", new Task());
        
        return modelAndView;
	} 
	
	@RequestMapping(value = "/assignTasks/getEmployees", headers = "Accept=*/*", method = RequestMethod.GET)
	public @ResponseBody
	    List<Employee> getEmployeeList(@RequestParam(value = "projectId", required = true) String projectId)  {
		return taskService.listEmployees(projectId);
	}
	
	private void addFeedbackMessage(RedirectAttributes attributes, String messageCode, Object... messageParameters) {
		        String localizedFeedbackMessage = getMessage(messageCode, messageParameters);
		        attributes.addFlashAttribute("feedbackMessage", localizedFeedbackMessage);
    }
		 
	private String getMessage(String messageCode, Object... messageParameters) {
		        Locale current = LocaleContextHolder.getLocale();
		        return messageSource.getMessage(messageCode, messageParameters, current);
	}
		 
	private String createRedirectViewPath(String requestMapping, String action) {
		        StringBuilder redirectViewPath = new StringBuilder();
		        redirectViewPath.append(action);
		        redirectViewPath.append(requestMapping);
		        return redirectViewPath.toString();
	}
}
