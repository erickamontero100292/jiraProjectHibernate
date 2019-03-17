package com.jiraproject.daoimpl;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jiraproject.interfacedao.BranchDAO;
import com.jiraproject.model.Branch;
import com.jiraproject.util.HibernateUtil;

public class BranchDAOImpl implements BranchDAO {
	

	public boolean save(Branch branch) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean commit=false;
		try {
		tx = session.beginTransaction();
		
		//session.save(branch);//Hibernate
		session.persist(branch); //JPA
		tx.commit();
		commit =true;
		session.close();
		
		}catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		return commit;

	}
}
