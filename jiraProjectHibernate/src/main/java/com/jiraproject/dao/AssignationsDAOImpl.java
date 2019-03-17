package com.jiraproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jiraproject.model.Assignations;
import com.jiraproject.util.HibernateUtil;

public class AssignationsDAOImpl implements AssignationsDAO {

	
	public void save(Assignations assignations) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		session.persist(assignations);
		tx.commit();
		session.close();
		
	}

}
