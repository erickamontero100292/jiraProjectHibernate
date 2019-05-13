package com.jiraproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;

@Entity
@Table(name = "assignations")
public class Assignations implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -1238350423754967000L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idassignations;
    private String nameassignation;
    private String description;
    @ManyToOne
    @JoinColumn(name = "idtypeasignations")
    private TypeAssignations typeAssignations2;


    public Assignations() {
    }

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
        return typeAssignations2;
    }

    public void setTypeAssignations(TypeAssignations typeAssignations) {
        this.typeAssignations2 = typeAssignations;
    }

    @Override
    public String toString() {
        return "Assignations [idassignations=" + idassignations + ", description=" + description + ", typeAssignations="
                + typeAssignations2.toString() + "]";
    }

    public TypeAssignations getTypeAssignations2() {
        return typeAssignations2;
    }

    public void setTypeAssignations2(TypeAssignations typeAssignations2) {
        this.typeAssignations2 = typeAssignations2;
    }

    public String getNameassignation() {
        return nameassignation;
    }

    public void setNameassignation(String nameassignation) {
        this.nameassignation = nameassignation;
    }
}
