package com.jiraproject.daoimpl;

import com.jiraproject.interfacedao.CommitsDAO;
import com.jiraproject.messages.Messages;
import com.jiraproject.model.Commits;
import com.jiraproject.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class CommitsDAOImpl implements CommitsDAO {

	public boolean save(Commits commits) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean commit = false;
		try {
			tx = session.beginTransaction();

			// session.save(commits);//Hibernate
			session.persist(commits); // JPA
			tx.commit();
			commit = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(Messages.BRANCH_EXIST.getMessage());
		} finally {
			session.close();
		}
		return commit;

	}

	public boolean update(Commits commits) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean update = false;
		try {
			tx = session.beginTransaction();
			session.update(commits);
			tx.commit();
			update = true;

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			System.out.println(Messages.BRANCH_EXIST.getMessage());

		} finally {
			session.close();
		}

		return update;
	}

	public boolean delete(Commits commits) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean delete = false;

		try {
			tx = session.beginTransaction();
			session.delete(commits);
			tx.commit();
			delete = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(Messages.BRANCH_EXIST.getMessage());
		} finally {
			session.close();

		}
		return delete;
	}

	public Commits loadByDescription(String description) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Commits commits = null;

		try {

			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Commits> criteria = builder.createQuery(Commits.class);
			Root<Commits> root = criteria.from(Commits.class);
			criteria.select(root);
//			criteria.where(builder.equal(root.get(Commits_.description), description));
			commits = session.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			session.close();
		}
		return commits;
	}

	@Override
	public List<Commits> loadCommitsAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Commits> listCommits = null;
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Commits> criteria = builder.createQuery(Commits.class);
			Root<Commits> root = criteria.from(Commits.class);
			criteria.select(root);
			listCommits = session.createQuery(criteria).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.unmodifiableList(listCommits);
	}

	@Override
	public Commits loadById(int id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Commits commits = null;
		Commits commitsNew = new Commits();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			commits = session.load(Commits.class, id);
			transaction.commit();
			commitsNew = new Commits();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}

		} finally {
			session.close();
		}
		return commitsNew;
	}


}