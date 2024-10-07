package org.zerock.myapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


// ============================================ //
// @ConfigurationProperties
//============================================ //
// When using @ConfigurationProperties,
// it is recommended to add 'spring-boot-configuration-processor' to your classpath to generate configuration metadata.
//
// <dependency>
//		<groupId>org.springframework.boot</groupId>
//		<artifactId>spring-boot-configuration-processor</artifactId>
//		<optional>true</optional>
// </dependency>
@ConfigurationProperties(prefix="board.jdbc")

@Data
public class JDBCConnectionManagerProperties {
	private String driverClass;
	private String url;
	private String username;
	private String password;
		

} // end class
