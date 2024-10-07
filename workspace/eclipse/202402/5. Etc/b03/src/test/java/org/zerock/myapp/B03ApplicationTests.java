package org.zerock.myapp;

import java.util.Objects;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.zerock.myapp.properties.AuthorProperties;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@EnableConfigurationProperties(AuthorProperties.class)

//@RunWith(SpringRunner.class)   // for JUnit 4
@SpringBootTest
class B03ApplicationTests {
	
	@Autowired
	private Environment env;
	
//	@Autowired
	@Setter(onMethod_= @Autowired)
	private AuthorProperties props;

	
	@Order(2)
	@Test
	void contextLoads() {
		log.info("contextLoads() invoked.");
		
		log.info("\t+ env: {}", env);
		log.info("\t\t+ author name: {}, age: {}, sex: {}, address: {}, nation: {}",
				env.getProperty("author.name"),
				env.getProperty("author.age"),
				env.getProperty("author.sex"),
				env.getProperty("author.address"),
				env.getProperty("nation"));
		
		log.info("\t+ props: {}", props);
		log.info("\t\t+ author name: {}, age: {}, sex: {}, address: {}, nation: {}",
				props.getName(),
				props.getAge(),
				props.getSex(),
				props.getAddress(),
				null);
	} // contextLoads
	
	
	@Order(1)
	@Test
	public void testSetProperties() {
		log.info("testSetProperties() invoked.");
		
		assert this.props != null;
		Objects.requireNonNull(this.props);
		
		// `application.properties` file NOT be changed.
		props.setName("Trinity");
		props.setAge(13);
		props.setSex("Male");
		props.setAddress("서울시 동작구 사당동");
		
		log.info(this.props);
	} // testMethod

} // end class
