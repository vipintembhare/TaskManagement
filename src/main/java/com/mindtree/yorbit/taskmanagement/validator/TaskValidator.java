package com.mindtree.yorbit.taskmanagement.validator;

import java.util.Date;
import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mindtree.yorbit.taskmanagement.model.Employee;
import com.mindtree.yorbit.taskmanagement.model.Task;

public class TaskValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
	 return Task.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		 ValidationUtils.rejectIfEmpty(errors, "taskDesc", "task.taskDesc.empty");
	      ValidationUtils.rejectIfEmpty(errors, "startDate", "task.startDate.empty");
	      ValidationUtils.rejectIfEmpty(errors, "endDate", "task.endDate.empty");

	      Task task = (Task) obj;
	      if (null == task.getProject()) {
	         errors.rejectValue("project", "task.project.null");
	      }
	      
	      Date startDate= task.getStartDate();
	      Date endDate = task.getEndDate();
	      if((null !=startDate  && null!=endDate ) && startDate.after(endDate)){
	    	  errors.rejectValue("endDate", "task.endDate.invalid");
	      }
	      
	      Set<Employee> employee=task.getAssignedResources();
	      if(null == employee || employee.isEmpty()){
	    	  errors.rejectValue("assignedResources", "task.assignedResources.invalid");
	      }
	
	}

}
