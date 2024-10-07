package org.zerock.myapp.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.zerock.myapp.domain.Person;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAQueryEntitiesByJPQL_5 {

	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
		final String persistenceUnitName = "quickstart-ch04-2";
		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		@Cleanup EntityManager em = emf.createEntityManager();
		
		// --- 1. JPQL -------------------------------
		// Note1: Entity name should be case-sensitive, If NOT setting `name` attribute of `@Entity` annotation in an Entity class.
		// Note2: Entity name should follow `name` attribute, If setting `name` attribute of `@Entity` annotation in an Entity class.
		
//		final String jpql = "SELECT p FROM Person p ORDER BY p.id DESC";		// if Note1
//		final String jpql = "SELECT p FROM person p ORDER BY p.id DESC";		// if Note2
		
//		TypedQuery<Person> typedQuery = em.<Person>createQuery(jpql, Person.class);
//		log.info("\t+ typedQuery: {}", typedQuery);
//		
//		List<Person> list = typedQuery.getResultList();
//		Objects.requireNonNull(list);
//		list.forEach(log::info);
//		list.clear();
		// ------------------------------------------

		// --- 2. Native Query  -----------------------
		final String nativeQuery = "SELECT * FROM person ORDER BY id DESC";
		Query query = em.createNativeQuery(nativeQuery, Person.class);
		
		@SuppressWarnings("unchecked")
		List<Person> list = (List<Person>) query.getResultList();
		list.forEach(log::info);
		// ------------------------------------------

		em.clear();
	} // main

} // end class

