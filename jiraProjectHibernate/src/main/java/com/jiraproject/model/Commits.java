package com.jiraproject.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "commits")
public class Commits implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcommits")
    private Long idcommits;
    private String sha;
    @ManyToOne
    @JoinColumn(name = "idbraches")
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "idasignations")
    private Assignations assignations;

    public Commits(Long idcommits, String sha, Branch branch, Assignations assignations) {
        this.idcommits = idcommits;
        this.sha = sha;
        this.branch = branch;
        this.assignations = assignations;
    }

    public Long getIdcommits() {
        return idcommits;
    }

    public void setIdcommits(Long idcommits) {
        this.idcommits = idcommits;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Assignations getAssignations() {
        return assignations;
    }

    public void setAssignations(Assignations assignations) {
        this.assignations = assignations;
    }
}
