package com.jiraproject.interfacedao;

import java.util.List;

import com.jiraproject.model.Branch;
import com.jiraproject.model.TypeAssignations;

public interface TypeAssignationsDAO {

	boolean save(TypeAssignations typeAssignations);
	
	boolean update(TypeAssignations typeAssignations);
	
	boolean delete(TypeAssignations typeAssignations);
	
	TypeAssignations loadByDescription (String description);
	
	TypeAssignations loadById (int id);
	
	List<TypeAssignations> loadTypeAssignationsAll ();
	
}
