package com.jiraproject.daoimpl;

import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.jiraproject.util.TransformerTypeAssignations;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jiraproject.interfacedao.TypeAssignationsDAO;
import com.jiraproject.messages.Messages;
import com.jiraproject.model.TypeAssignations;
import com.jiraproject.model.TypeAssignations_;
import com.jiraproject.util.HibernateUtil;

public class TypeAssignationsDAOImpl implements TypeAssignationsDAO {

	@Override
	public boolean save(TypeAssignations typeAssignations) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean commit = false;
		try {
			tx = session.beginTransaction();
			session.persist(typeAssignations); // JPA
			tx.commit();
			commit = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(Messages.TYPE_ASIGNATION_EXIST.getMessage());
		} finally {
			session.close();
		}
		return commit;
	}

	@Override
	public boolean update(TypeAssignations typeAssignations) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean update = false;
		try {
			tx = session.beginTransaction();
			session.update(typeAssignations);
			tx.commit();
			update = true;

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			System.out.println(Messages.TYPE_ASIGNATION_EXIST.getMessage());

		} finally {
			session.close();
		}

		return update;
	}

	@Override
	public boolean delete(TypeAssignations typeAssignations) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean delete = false;

		try {
			tx = session.beginTransaction();
			session.delete(typeAssignations);
			delete = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(Messages.TYPE_ASIGNATION_EXIST.getMessage());
		} finally {
			session.close();
		}
		return delete;
	}

	@Override
	public TypeAssignations loadByDescription(String description) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		TypeAssignations assignations = null;
		TypeAssignations assignationsDB = null;

		try {
			
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery< TypeAssignations> criteriaQuery = builder.createQuery(TypeAssignations.class);
			Root<TypeAssignations> root = criteriaQuery.from(TypeAssignations.class);
			criteriaQuery.select(root);
			description = evaluateDescription(description);
			criteriaQuery.where(builder.equal(root.get(TypeAssignations_.description), description));
			assignationsDB = session.createQuery(criteriaQuery).getSingleResult();
			assignations = new TypeAssignations(assignationsDB.getIdTypeAsiggnations(), assignationsDB.getDescription(), assignationsDB.getSetAssignations());

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			session.close();
		}
		return assignations;
	}

	private String evaluateDescription(String description) {
		if(description.equalsIgnoreCase(TransformerTypeAssignations.STORY.getAssignationName())){
			return TransformerTypeAssignations.STORY.getTransformName();
		}
		return description;
	}

	@Override
	public TypeAssignations loadById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TypeAssignations assignations = null;
		TypeAssignations assignationsDB = null;
		try {

			
			assignationsDB = session.load(TypeAssignations.class, id);
			assignations = new TypeAssignations(assignationsDB.getIdTypeAsiggnations(), assignationsDB.getDescription(), assignationsDB.getSetAssignations());


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return assignations;
	}

	@Override
	public List<TypeAssignations> loadTypeAssignationsAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<TypeAssignations> lisTypeAssignations = null;

		try {
			
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery< TypeAssignations> criteriaQuery = builder.createQuery(TypeAssignations.class);
			Root<TypeAssignations> root = criteriaQuery.from(TypeAssignations.class);
			criteriaQuery.select(root);
			lisTypeAssignations = session.createQuery(criteriaQuery).getResultList();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			session.close();
		}
		return Collections.unmodifiableList(lisTypeAssignations);
	}

}
