package org.zerock.myapp;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ViewResolver;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc

@SpringBootTest(
		webEnvironment = WebEnvironment.MOCK,
//		webEnvironment = WebEnvironment.DEFINED_PORT,
		
//		properties = "spring.jpa.hibernate.ddl-auto=create"
		properties = "spring.jpa.hibernate.ddl-auto=update"
	)
class QuickstartCh06AppTests {
	@Autowired(required = false) 	private MockMvc mockMvc;
	@Autowired(required = false) 	private DataSource dataSource;
	@Autowired(required = false) 	private Environment env;
	@Autowired(required = false) 	private ApplicationContext ctx;
	@PersistenceUnit 						private EntityManagerFactory emf;
	@PersistenceContext 					private EntityManager em;
	
	@Resource(name = "defaultViewResolver")
	private ViewResolver defaultViewResolver;
	
	@Value("${spring.mvc.view.prefix}")
	private String viewResolverPrefix;
	
	@Value("${spring.mvc.view.suffix}")
	private String viewResolverSuffix;

	
	@PostConstruct
	void postConstruct( ) {
		log.trace("postConstruct() invoked.");
		
		log.info("  1. this.mockMvc: {}", this.mockMvc);
		log.info("  2. this.dataSource: {}", this.dataSource);
		log.info("  3. this.env: {}", this.env);
		log.info("  4. this.ctx: {}", this.ctx);
		log.info("  5. this.emf: {}", this.emf);
		log.info("  6. this.em: {}", this.em);
		log.info("  7. this.defaultViewResolver: {}", this.defaultViewResolver);
		
		log.info("  8. this.viewResolverPrefix: {}", this.viewResolverPrefix);
		log.info("  9. this.viewResolverSuffix: {}", this.viewResolverSuffix);
	} // postConstruct
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(this.ctx);
		String[] beanDefinitionNames = this.ctx.getBeanDefinitionNames();
		for(String beanDefinitionName : beanDefinitionNames) {
			log.trace("  + beanDefinitionName: {}", beanDefinitionName);
		} // enhanced for
	} // beforeAll
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void contextLoads() {
		log.trace("contextLoads() invoked.");
		
	} // contextLoads

} // end class

