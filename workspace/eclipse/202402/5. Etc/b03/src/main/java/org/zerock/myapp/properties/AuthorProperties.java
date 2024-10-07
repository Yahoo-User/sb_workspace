package org.zerock.myapp.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;



//============================================ //
// @ConfigurationProperties
//============================================ //
// When using `@ConfigurationProperties`,
// it is recommended to add `spring-boot-configuration-processor`
// to your class path to generate configuration metadata.
//
//<dependency>
//		<groupId>org.springframework.boot</groupId>
//		<artifactId>spring-boot-configuration-processor</artifactId>
//		<optional>true</optional>
//</dependency>
@ConfigurationProperties(prefix="author")

@Data
public class AuthorProperties {
	
	private String name;
	private int age;
	private String sex;
	private String address;

	
} // end class
