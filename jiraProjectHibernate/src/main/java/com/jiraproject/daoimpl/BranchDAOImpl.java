package com.jiraproject.daoimpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.jiraproject.interfacedao.BranchDAO;
import com.jiraproject.model.Branch;
import com.jiraproject.model.Branch_;
import com.jiraproject.util.HibernateUtil;

public class BranchDAOImpl implements BranchDAO {

	public boolean save(Branch branch) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean commit = false;
		try {
			tx = session.beginTransaction();

			// session.save(branch);//Hibernate
			session.persist(branch); // JPA
			tx.commit();
			commit = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("El nombre del branch ya existe ");
		} finally {
			session.close();
		}
		return commit;

	}

	public boolean update(Branch branch) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean update = false;
		try {
			tx = session.beginTransaction();
			session.update(branch);
			tx.commit();
			update = true;

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			System.out.println("El nombre del branch ya existe ");

		} finally {
			session.close();
		}

		return update;
	}

	public boolean delete(Branch branch) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean delete = false;

		try {
			tx = session.beginTransaction();
			session.delete(branch);
			tx.commit();
			delete = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("El nombre del branch ya existe ");
		} finally {
			session.close();

		}
		return delete;
	}

	public Branch loadByDescription(String description) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Branch branch = null;

		try {

			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Branch> criteria = builder.createQuery(Branch.class);
			Root<Branch> root = criteria.from(Branch.class);
			criteria.select(root);
			criteria.where(builder.equal(root.get(Branch_.description), description));
			branch = session.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			session.close();
		}
		return branch;
	}

	@Override
	public List<Branch> loadBrachAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Branch> listBranch = null;
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Branch> criteria = builder.createQuery(Branch.class);
			Root<Branch> root = criteria.from(Branch.class);
			criteria.select(root);
			listBranch = session.createQuery(criteria).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.unmodifiableList(listBranch);
	}

	/*
	 * @Override public Branch loadById(int id) {
	 * 
	 * Session session = HibernateUtil.getSessionFactory().openSession(); Branch
	 * branch = null; try{ TypedQuery<Branch> query
	 * =session.createQuery("SELECT B FROM BRANCHES WHERE b.idbranches =:id",Branch.
	 * class); query.setParameter("id", id); branch = query.getSingleResult();
	 * }catch (Exception e) { e.printStackTrace(); } finally { session.close(); }
	 * return branch; }
	 */

	@Override
	public Branch loadById(int id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Branch branch = null;
		Branch branchNew = new Branch();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			branch = session.load(Branch.class, id);
			transaction.commit();
			branchNew = new Branch(branch.getIdBranches(),branch.getDescription(),branch.getDatecreated());
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}

		} finally {
			session.close();
		}
		return branchNew;
	}
}