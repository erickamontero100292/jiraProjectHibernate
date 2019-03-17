package com.jiraproject.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jiraproject.interfacedao.TypeAssignationsDAO;
import com.jiraproject.model.TypeAssignations;
import com.jiraproject.util.HibernateUtil;

public class TypeAssignationsDAOImpl implements TypeAssignationsDAO {



	public void save(TypeAssignations typeAssignation) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		session.persist(typeAssignation);
		tx.commit();
		session.close();

	}


	public TypeAssignations loadById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		TypeAssignations typeAssignations = (TypeAssignations) session.load(TypeAssignations.class, id);	
		//typeAssignations.getIdTypeAsiggnations();
		session.close();
		return typeAssignations;
	}

}
