 package com.mindtree.yorbit.taskmanagement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.yorbit.taskmanagement.converters.ProjectIdToProjectConverter;

@Entity
@Table(name="TASK")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TASK_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer taskId;
	
	@Column(name="TASK_DESC")
	private String taskDesc;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROJECT_ID")
	private Project project;
	
	@ManyToMany
	@JoinTable(name = "EMP_TASK", joinColumns = { 
			@JoinColumn(name = "TASK_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "EMP_ID", 
					nullable = false, updatable = false) })
	private Set<Employee> assignedResources;
	private Date startDate;
	private Date endDate;
	
	//getters and setters
	public Integer getTaskId() {
		return taskId;
	}
	
	public Set<Employee> getAssignedResources() {
		return assignedResources;
	}

	public void setAssignedResources(Set<Employee> assignedResources) {
		this.assignedResources = assignedResources;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="STARTDATE")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="ENDDATE")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	//hashcode and equals implementation
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((assignedResources == null) ? 0 : assignedResources.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((taskDesc == null) ? 0 : taskDesc.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
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
		Task other = (Task) obj;
		if (assignedResources == null) {
			if (other.assignedResources != null)
				return false;
		} else if (!assignedResources.equals(other.assignedResources))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (taskDesc == null) {
			if (other.taskDesc != null)
				return false;
		} else if (!taskDesc.equals(other.taskDesc))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
				return true;
	}
	
}

