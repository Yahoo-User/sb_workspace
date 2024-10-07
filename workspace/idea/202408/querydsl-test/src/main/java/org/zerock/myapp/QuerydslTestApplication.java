package org.zerock.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;


@EntityScan
@ServletComponentScan
@ConfigurationPropertiesScan

@SpringBootApplication
public class QuerydslTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuerydslTestApplication.class, args);
	}

}
