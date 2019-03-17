package com.jiraproject.interfacedao;

import com.jiraproject.model.TypeAssignations;

public interface TypeAssignationsDAO {

	void save (TypeAssignations typeAssignation);
	
	TypeAssignations loadById(int id);
	
}
