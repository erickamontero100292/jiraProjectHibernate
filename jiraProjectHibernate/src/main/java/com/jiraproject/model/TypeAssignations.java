package com.jiraproject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="type_assignations")
public class TypeAssignations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4431330235909220755L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtypeassignations")
	private int idTypeAsiggnations;
	private String 	description;
	public int getIdTypeAsiggnations() {
		return idTypeAsiggnations;
	}
	public void setIdTypeAsiggnations(int idTypeAsiggnations) {
		this.idTypeAsiggnations = idTypeAsiggnations;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "TypeAssignations [idTypeAsiggnations=" + idTypeAsiggnations + ", description=" + description + "]";
	}
	
	
}
