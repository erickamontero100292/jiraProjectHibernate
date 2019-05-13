package com.jiraproject.interfacedao;

import java.util.List;

import com.jiraproject.model.Branch;

public interface BranchDAO {
	
	boolean save(Branch branch);
	
	boolean update(Branch branch);
	
	boolean delete(Branch branch);
	
	Branch loadByDescription (String description);
	
	Branch loadById (int id);
	
	List<Branch> loadBrachAll ();
	
}
