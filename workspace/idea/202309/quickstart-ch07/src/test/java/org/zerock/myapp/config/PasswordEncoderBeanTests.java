package org.zerock.myapp.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.zerock.myapp.entity.Member;
import org.zerock.myapp.entity.Role;
import org.zerock.myapp.persistence.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

// `@Commit` is a test annotation that is used to indicate that a test-managed transaction should be committed
// after the test method has completed.
@Commit
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PasswordEncoderBeanTests {
	@Autowired private MemberRepository memberRepository;
	@Autowired private PasswordEncoder passwordEncoder;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertThat(this.memberRepository).isNotNull();
		log.info("\t+ this.memberRepository: {}", this.memberRepository);

		assertThat(this.passwordEncoder).isNotNull();
		log.info("\t+ this.passwordEncoder: {}", this.passwordEncoder);
	} // beforeAll
	
//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("testInsertEncryptedUser")
	@Timeout(3L)
	void testInsertEncryptedUser() {
		log.trace("testInsertEncryptedUser() invoked.");

		Member member = new Member();
		member.setId("Yoseph");
		member.setPassword(this.passwordEncoder.encode("Yoseph123"));
		member.setName("SuperUser");
		member.setRole(Role.ROLE_ADMIN);
		member.setEnabled(true);

		log.info("\t+ member: {}", member);

		this.memberRepository.save(member);
	} // testInsertEncryptedUser

} // end class
