package com.mindtree.yorbit.taskmanagement.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.mindtree.yorbit.taskmanagement.dao.TaskDao;
import com.mindtree.yorbit.taskmanagement.model.Employee;

public class EmpIdtoEmployeeConverter implements Converter<String, Employee>{

	@Autowired
	TaskDao taskDao;
	@Override
	public Employee convert(String empId) {
		return taskDao.findEmployeeByEmpId(empId);
	}

	
}