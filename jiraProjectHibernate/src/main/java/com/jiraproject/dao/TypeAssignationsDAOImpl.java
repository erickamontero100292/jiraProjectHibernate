package com.jiraproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jiraproject.model.TypeAssignations;

public class TypeAssignationsDAOImpl implements TypeAssignationsDAO {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


	public void save(TypeAssignations typeAssignation) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(typeAssignation);
		tx.commit();
		session.close();

	}

}
