package com.jiraproject.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "branches")
public class Branch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8474505847730884908L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idBranches;
	private String description;
	private Timestamp datecreated;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIdBranches() {
		return idBranches;
	}
	public void setIdBranches(int idBranches) {
		this.idBranches = idBranches;
	}
	
	
	public Timestamp getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(Timestamp datecreated) {
		this.datecreated = datecreated;
	}
	
	
	public Branch(int idBranches, String description, Timestamp datecreated) {
		super();
		this.idBranches = idBranches;
		this.description = description;
		this.datecreated = datecreated;
	}
	
	public Branch() {
		super();
	}
	@Override
	public String toString() {
		return "Branch [idBranches=" + idBranches + ", description=" + description + ", datecreated=" + datecreated
				+ "]";
	}

	
	
	
	
}
