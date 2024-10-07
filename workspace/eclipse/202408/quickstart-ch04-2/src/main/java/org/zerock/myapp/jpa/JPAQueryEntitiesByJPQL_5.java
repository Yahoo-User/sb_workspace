package org.zerock.myapp.jpa;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.zerock.myapp.domain.Person;
import org.zerock.myapp.util.Persistences;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAQueryEntitiesByJPQL_5 {

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

		//-- 1 ------------
		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistences.persistenceUnitName);
		@Cleanup EntityManager em = emf.createEntityManager();

		
		//-- 2 ------------
		// Using JPQL
		
		/**
		 * Note1: Entity name should be case-sensitive, If NOT setting `name` attribute of `@Entity` annotation in an Entity class.
		 * Note2: Entity name should follow `name` attribute, If setting `name` attribute of `@Entity` annotation in an Entity class.
		 */
		
		final String jpql = "SELECT p FROM Person p ORDER BY p.id DESC";		// if Note1
//		final String jpql = "SELECT p FROM person p ORDER BY p.id DESC";		// if Note2
		
		TypedQuery<Person> typedQuery = em.<Person>createQuery(jpql, Person.class);
		log.info("\t+ typedQuery: {}", typedQuery);
		
		typedQuery.getResultList().forEach(log::info);

		
		//-- 3 ------------
		// Using Native Query

		final String nativeQuery = "SELECT * FROM person ORDER BY person_id DESC";
		Query query = em.createNativeQuery(nativeQuery, Person.class);
		
		query.getResultList().forEach(log::info);

		
		//-- 4 ------------
		em.clear();
	} // main

} // end class

