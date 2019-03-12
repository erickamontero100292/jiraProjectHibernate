package com.jiraproject.model;
import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="assignations")
public class Assignations implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1238350423754967000L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idassignations;
	private String description;
	@ManyToOne
	@JoinColumn (name="idtypeassignations")
	private TypeAssignations typeAssignations;
	
	
	public int getIdassignations() {
		return idassignations;
	}
	public void setIdassignations(int idassignations) {
		this.idassignations = idassignations;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TypeAssignations getTypeAssignations() {
		return typeAssignations;
	}
	public void setTypeAssignations(TypeAssignations typeAssignations) {
		this.typeAssignations = typeAssignations;
	}
	@Override
	public String toString() {
		return "Assignations [idassignations=" + idassignations + ", description=" + description + ", typeAssignations="
				+ typeAssignations + "]";
	}
	

}