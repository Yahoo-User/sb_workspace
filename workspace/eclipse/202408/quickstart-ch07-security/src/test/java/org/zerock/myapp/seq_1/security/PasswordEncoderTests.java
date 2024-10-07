package org.zerock.myapp.seq_1.security;

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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PasswordEncoderTests {
	private PasswordEncoder encoder;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		this.encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		log.info("  + this.encoder: {}", this.encoder);
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testEncodeWithBCryptPasswordEncoder")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testEncodeWithBCryptPasswordEncoder() {
		log.trace("testEncodeWithBCryptPasswordEncoder");
		
		String rawPassword = "1234";
		String bcryptedPassword = this.encoder.encode(rawPassword);
		
		log.info("  + bcryptedPassword: {}", bcryptedPassword);
	} // testEncodeWithBCryptPasswordEncoder
	
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testDecodeWithBCryptPasswordEncoder")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testDecodeWithBCryptPasswordEncoder() {
		log.trace("testDecodeWithBCryptPasswordEncoder");
		
		String rawPassword = "1234";
		String bcryptedPassword = "{bcrypt}$2a$10$Ha4RkwRhqw0O/yIWLccTbeDiFQMp2CV51WwbwauymiJRV9I2yEUO2";	// 1234
		
		boolean isMatched = this.encoder.matches(rawPassword, bcryptedPassword);
		log.info("  + isMatched: {}", isMatched);
	} // testDecodeWithBCryptPasswordEncoder
	
	

} // end class

