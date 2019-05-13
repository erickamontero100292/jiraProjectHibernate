package com.jiraproject.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Commits.class)
public abstract class Commits_ {

	public static volatile SingularAttribute<Commits, Assignations> assignations;
	public static volatile SingularAttribute<Commits, Long> idcommits;
	public static volatile SingularAttribute<Commits, String> sha;
	public static volatile SingularAttribute<Commits, Branch> branch;

}

