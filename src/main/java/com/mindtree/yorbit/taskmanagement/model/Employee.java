package com.mindtree.yorbit.taskmanagement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.yorbit.taskmanagement.converters.LocalDateConverter;

@Entity
@Table(name="EMPLOYEE")
@JsonIgnoreProperties({ "tasks" })
public class Employee implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empId;
	private String empName;
	private String designation;
	private String email;
	private Integer manager;
	
	private Department department;
	
	private Date dateOfJoining;
	private Set<Task> tasks;
	private Project project;
	@Id
	@Column(name="EMP_ID")
	public String getEmpId() {
		return empId;
	}
	@ManyToMany(mappedBy = "assignedResources")
	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	@Column(name="EMP_NAME")
	public String getEmpName() {
		return empName;
	}
	@Column(name="MANAGER_ID")
	public Integer getManager() {
		return manager;
	}
	public void setManager(Integer manager) {
		this.manager = manager;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@Column(name="DESIGNATION")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="DEPT_ID")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	//@Convert(converter = LocalDateConverter.class)
	
	@Column(name="DATE_OF_JOINING")
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="PROJECT_ID")
    public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	//hashcode and equals implementation
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateOfJoining == null) ? 0 : dateOfJoining.hashCode());
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
		result = prime * result
				+ ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (dateOfJoining == null) {
			if (other.dateOfJoining != null)
				return false;
		} else if (!dateOfJoining.equals(other.dateOfJoining))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		return true;
	}
	
	@PrePersist
	private void ensureId(){
	    this.setEmpId(UUID.randomUUID().toString());
	}
	
}
