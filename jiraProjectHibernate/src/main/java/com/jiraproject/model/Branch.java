package com.jiraproject.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "branches")
public class Branch implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8474505847730884908L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBranches;
    private String description;
    private Timestamp datecreated;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "branch",
            orphanRemoval = true
    )
    private List<Commits> commitsList = new ArrayList<Commits>();


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


    public Branch(String description, Timestamp datecreated) {
        super();
        this.description = description;
        this.datecreated = datecreated;
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


    public List<Commits> getCommitsList() {
        return commitsList;
    }

    public void setCommitsList(List<Commits> commitsList) {
        this.commitsList = commitsList;
    }
}
