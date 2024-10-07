package org.zerock.myapp.seq_5.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


// (1) Make an immutable object with `@ConstructorBinding` annotation.
//@Accessors(fluent = true)																	// OK
//@AllArgsConstructor(onConstructor_= @ConstructorBinding)		// OK
//@Value

// (2) Make an mutable object with `@ConfigurationProperties` annotation.
@Data																										// OK

@ConfigurationProperties(prefix = "user-defined")
public class UserDefinedProperties {
	private String name;
	
//	private String age;				// OK
	private Integer age;				// OK
	
	private String prop1;
	private String prop2;
	private String prop3;
	private String prop4;
	private String myProperty;	// OK, user-defined.my_property in application.properties.
	
	
	
	

} // end class
