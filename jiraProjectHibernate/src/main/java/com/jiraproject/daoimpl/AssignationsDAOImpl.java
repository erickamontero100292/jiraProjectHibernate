package com.jiraproject.daoimpl;

import com.jiraproject.messages.Messages;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jiraproject.interfacedao.AssignationsDAO;
import com.jiraproject.model.Assignations;
import com.jiraproject.util.HibernateUtil;

public class AssignationsDAOImpl implements AssignationsDAO {


    public boolean save(Assignations assignations) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean commit = false;
        try {
            tx = session.beginTransaction();
            session.persist(assignations); // JPA
            tx.commit();
            commit = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(Messages.ERROR_SAVE.getMessage());
        } finally {
            session.close();
        }
        return commit;

    }

}
