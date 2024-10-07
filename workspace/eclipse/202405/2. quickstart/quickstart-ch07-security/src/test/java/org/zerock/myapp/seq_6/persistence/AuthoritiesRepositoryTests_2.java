package org.zerock.myapp.seq_6.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.myapp.seq_4.domain.Authorities;
import org.zerock.myapp.seq_5.persistence.AuthoritiesRepository;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest(
	webEnvironment = WebEnvironment.MOCK,
//	webEnvironment = WebEnvironment.DEFINED_PORT,
	
//	properties = "spring.jpa.hibernate.ddl-auto=create"
	properties = "spring.jpa.hibernate.ddl-auto=update"
)
public class AuthoritiesRepositoryTests_2 {
	@Autowired(required = true)
	private AuthoritiesRepository authoritiesRepo;
	
	private PasswordEncoder bcryptEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.authoritiesRepo);
		log.info("  + this.authoritiesRepo: {}", this.authoritiesRepo);
		log.info("  + this.bcryptEncoder: {}", this.bcryptEncoder);
	} // beforeAll
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("prepareData")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void prepareData() {
		log.trace("prepareData() invoked.");
		
		String username = "", authorityUser = "USER", authorityAdmin = "ADMIN";
		
		// ---------
		username = "Yoseph";
		
		Authorities transientYosephWithUser = new Authorities();
		transientYosephWithUser.setUsername(username);
		transientYosephWithUser.setAuthority(authorityUser);
		
		this.authoritiesRepo.save(transientYosephWithUser);
		
		// ---------
		username = "Yoseph";
		
		Authorities transientYosephWithAdmin = new Authorities();
		transientYosephWithAdmin.setUsername(username);
		transientYosephWithAdmin.setAuthority(authorityAdmin);
		
		this.authoritiesRepo.save(transientYosephWithAdmin);
		
		// ---------
		username = "Trinity";
		
		Authorities transientTrinityWithUser = new Authorities();
		transientTrinityWithUser.setUsername(username);
		transientTrinityWithUser.setAuthority(authorityUser);
		
		this.authoritiesRepo.save(transientTrinityWithUser);
		
		// ---------
		username = "Pyramid";
		
		Authorities transientPyramidWithAdmin = new Authorities();
		transientPyramidWithAdmin.setUsername(username);
		transientPyramidWithAdmin.setAuthority(authorityAdmin);
		
		this.authoritiesRepo.save(transientPyramidWithAdmin);
	} // prepareData
	
	
} // end class
