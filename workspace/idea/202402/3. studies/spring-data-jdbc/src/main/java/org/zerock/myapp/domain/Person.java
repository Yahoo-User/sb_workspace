package org.zerock.myapp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;


@Data

// -------------------------------------------
// https://www.baeldung.com/spring-data-jdbc-intro
//
// We don’t need to use the annotation `@Table` or `@Column` in the Person class.
// The default naming strategy of Spring Data JDBC does all the mappings implicitly between the entity and the table.
// -------------------------------------------
//@Table(name = "PERSON")
public class Person {
	@Id private Long id;
	
	private String name;
	private Integer age;
	

	
} // end class
