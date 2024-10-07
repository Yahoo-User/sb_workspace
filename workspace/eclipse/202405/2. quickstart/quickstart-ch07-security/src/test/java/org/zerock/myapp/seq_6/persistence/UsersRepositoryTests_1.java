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
import org.zerock.myapp.seq_4.domain.Users;
import org.zerock.myapp.seq_5.persistence.UsersRepository;

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
public class UsersRepositoryTests_1 {
	@Autowired(required = true) private UsersRepository usersRepo;
	private PasswordEncoder bcryptEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.usersRepo);
		log.info("  + this.usersRepo: {}", this.usersRepo);
	} // beforeAll
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("prepareData")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void prepareData() {
		log.trace("prepareData() invoked.");
		
		String username = "", password = "";
		
		// ---------
		username = "Yoseph"; password = "1234";
		
		Users transientYoseph = new Users();
		transientYoseph.setUsername(username);
		transientYoseph.setPassword(bcryptEncoder.encode(password));
		transientYoseph.setEnabled(true);
		
		// ---------
		username = "Trinity"; password = "1234";
		
		Users transientTrinity = new Users();
		transientTrinity.setUsername(username);
		transientTrinity.setPassword(bcryptEncoder.encode(password));
		transientTrinity.setEnabled(true);
		
		// ---------
		username = "Pyramid"; password = "1234";
		
		Users transientPyramid = new Users();
		transientPyramid.setUsername(username);
		transientPyramid.setPassword(bcryptEncoder.encode(password));
		transientPyramid.setEnabled(true);

		// ---------
		this.usersRepo.save(transientYoseph);
		this.usersRepo.save(transientTrinity);
		this.usersRepo.save(transientPyramid);
	} // prepareData
	
	
} // end class
