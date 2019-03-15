package com.mindtree.yorbit.taskmanagement.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT")
public class Project implements Serializable,Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String projectId;
	private String projectName;
	private String projectDesc;
	private Department department; 
	//getters and setters
	@Id
	@Column(name="PROJECT_ID")
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Column(name="PROJECT_NAME")
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Column(name="PROJECT_DESC")
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="DEPT_ID")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
		result = prime * result
				+ ((projectDesc == null) ? 0 : projectDesc.hashCode());
		result = prime * result
				+ ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result
				+ ((projectName == null) ? 0 : projectName.hashCode());
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
		Project other = (Project) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (projectDesc == null) {
			if (other.projectDesc != null)
				return false;
		} else if (!projectDesc.equals(other.projectDesc))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}
	
	@PrePersist
	private void ensureId(){
	    this.setProjectId(UUID.randomUUID().toString());
	}
	
	
	
}
