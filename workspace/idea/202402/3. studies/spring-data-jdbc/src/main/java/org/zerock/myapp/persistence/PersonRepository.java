package org.zerock.myapp.persistence;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.Person;


// -------------------------------------------
// Transaction With Spring Data JDBC (***)
// -------------------------------------------
// 1. https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
// -------------------------------------------
//
// 1. if we’re using a (1) `Spring Boot project` and have a (2) `spring-data-*` or `spring-tx` dependencies
//    on the classpath, then transaction management will be enabled by default.
// 2. With transactions configured,
// 	  we can now annotate a bean with `@Transactional` either at the `class` or `method` level.
//
//    	@Service
//		@Transactional
//		public class FooService {
//    		//...
//		}
//
// 3. The `@Transactional`annotation supports further configuration as well:
// 		- the Propagation Type of the transaction
//		- the Isolation Level of the transaction
//		- a Timeout for the operation wrapped by the transaction
//		- a readOnly flag – a hint for the persistence provider that
//		  the transaction should be read only the Rollback rules for the transaction
//
//    Note that by default, rollback happens for `runtime`, `unchecked` exceptions `only`.
//    The `checked` exception does not trigger a rollback of the transaction.
//    We can, of course, configure this behavior with the `rollbackFor` and `noRollbackFor` annotation parameters.
// -------------------------------------------
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	// -------------------------------------------------------
	// 1. JPA Query Methods Declaration and JPA Query Annotation.
	// -------------------------------------------------------
	List<Person> findByName(String name);

	// -------------------------------------------------------
	// 2. JPA Query Annotations.
	// -------------------------------------------------------
	@Modifying	// Indicates a method should be regarded as modifying query.
	@Query("UPDATE person SET name = :name WHERE id = :id")
    boolean updateByName(@Param("id") Long id, @Param("name") String name);


} // end interface
