package com.jiraproject.interfacedao;

import com.jiraproject.model.Commits;

import java.util.List;

public interface CommitsDAO {
	
	boolean save(Commits commits);
	
	boolean update(Commits commits);
	
	boolean delete(Commits commits);
	
	Commits loadByDescription(String description);
	
	Commits loadById(int id);
	
	List<Commits> loadCommitsAll();
	
}
