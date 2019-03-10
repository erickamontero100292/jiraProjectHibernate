package com.jiraproject.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jiraproject.model.Branch;

public class BranchDAOImpl implements BranchDAO {
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	public void save(Branch branch) {
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(branch);//Hibernate
		session.persist(branch); //JPA
		tx.commit();
		session.close();

	}

}
