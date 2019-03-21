package com.jiraproject.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Branch.class)
public abstract class Branch_ {

	public static volatile SingularAttribute<Branch, String> description;
	public static volatile SingularAttribute<Branch, Integer> idBranches;
	public static volatile SingularAttribute<Branch, Timestamp> datecreated;

}

