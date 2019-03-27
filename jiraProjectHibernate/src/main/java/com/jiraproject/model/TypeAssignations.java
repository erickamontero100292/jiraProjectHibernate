package com.jiraproject.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="type_assignations")
public class TypeAssignations implements Serializable{

	public TypeAssignations() {
		super();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 4431330235909220755L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtypeassignations")
	private int idTypeAsiggnations;
	private String 	description;
	
	//@OneToMany(cascade = CascadeType.ALL,	        orphanRemoval = true)
	//@JoinColumn(name = "idtypeassignations")
	 @OneToMany(
		        mappedBy = "typeAssignations2",
		        cascade = CascadeType.ALL,
		        orphanRemoval = true
		    )
	private Set<Assignations> setAssignations;
	
	
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
	
	public Set<Assignations> getSetAssignations() {
		return setAssignations;
	}
	public void setSetAssignations(Set<Assignations> setAssignations) {
		this.setAssignations = setAssignations;
	}
	@Override
	public String toString() {
		return "TypeAssignations [idTypeAsiggnations=" + idTypeAsiggnations + ", description=" + description
				+ ", setAssignations=" + setAssignations.toString() + "]";
	}
	public TypeAssignations(String description) {
		super();
		this.description = description;
	}
	public TypeAssignations(String description, Set<Assignations> setAssignations) {
		super();
		this.description = description;
		this.setAssignations = setAssignations;
	}
	public TypeAssignations(int idTypeAsiggnations, String description, Set<Assignations> setAssignations) {
		super();
		this.idTypeAsiggnations = idTypeAsiggnations;
		this.description = description;
		this.setAssignations = setAssignations;
	}
	
	
}
