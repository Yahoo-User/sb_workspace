package org.zerock.myapp.jpa;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import org.zerock.myapp.domain.Person;
import org.zerock.myapp.util.Persistences;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAEntityManagerFactory_1 {
	
	
	/**
	 * -----------------------------------------
	 * 1. Interface EntityManagerFactory
	 * -----------------------------------------
	 * Interface used to interact with the entity manager factory for the persistence unit.
	 * 
	 * When the application has finished using the entity manager factory, and/or at application shutdown,
	 * the application should `close` the entity manager factory. 
	 * 
	 * Once an EntityManagerFactory has been closed, all its entity managers are considered to be in the `closed` state.
	 * 
	 * 		Since:	Java Persistence 1.0
	 * 
	 * -----------------------------------------
	 * 2. Persistence.createEntityManagerFactory(persistenceUnitName) method
	 * -----------------------------------------
	 * Create and return an EntityManagerFactory for the named persistence unit.
	 * 
	 * 		Parameters:		persistenceUnitName the name of the persistence unit
	 * 		Returns:			the factory that creates EntityManagers configured according to the specified persistence unit
	 * 
	 * -----------------------------------------
	 * 3. EntityManagerFactory.close() method
	 * -----------------------------------------
	 * invoked on it will throw the IllegalStateException, except for isOpen, which will return false.
	 * Once an EntityManagerFactory has been closed, all its entity managers are considered to be in the closed state.
	 * 
	 * 		Throws: IllegalStateException - if the entity manager factory has been closed
	 */
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
		//-- 1 ------------
		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistences.persistenceUnitName);

		log.info("\t+ emf: {}", emf);
		log.info("\t+ isOpen: {}", emf.isOpen());

		
		//-- 2 ------------
		Map<String, Object> properties = emf.getProperties();
		
		properties.forEach((k,v) -> {
			log.info("\t+ ({}, {})", k, v);
		});	// .forEach

		
		//-- 3 ------------
		Metamodel metamodel = emf.getMetamodel();
		log.info("\t+ metamodel: {}", metamodel);

		
		//-- 4 ------------
		Set<EntityType<?>> entityTypes = metamodel.getEntities();
		
		entityTypes.forEach(type -> {
			log.info("\t+ Entity Type: {}", type);
		});		// Entity Class Simple Names

		
		//-- 5 ------------
		Set<ManagedType<?>> managedTypes = metamodel.getManagedTypes();
		
		managedTypes.forEach((type) -> {
			log.info("\t+ Managed Type: {}", type);
		});		// Entity Class Simple Names

		
		//-- 6 ------------
		Person person = new Person();
		person.setName("NAME_1");
		person.setAge(23);
		
		log.info("\t+ person: {}", person);

		
		//-- 7 ------------
		PersistenceUnitUtil util = emf.getPersistenceUnitUtil();
		log.info("\t+ isLoaded: {}", util.isLoaded(person));					// true

		
		//-- 8 ------------
//		emf.close();
	} // main

} // end class

