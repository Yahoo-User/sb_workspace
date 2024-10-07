package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.domain.Role;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/*
 * ===================================
 * `@Commit` Annotation
 * ===================================
 * `@Commit` is a test annotation that is used to indicate that a test-managed transaction should be committed
 * after the test method has completed.
 *
 */
//@Commit

/*
 * ===================================
 * `@AutoConfigureMockMvc` Annotation
 * ===================================
 * Annotation that can be applied to a test class to enable and configure auto-configuration of `MockMvc`.
 *
 */
@AutoConfigureMockMvc

// With mocking Tomcat servlet container.
@SpringBootTest
// With Tomcat embedded servlet container.
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MemberRepositoryTests {
	@Autowired private MockMvc mockMvc;
	@Autowired private MemberRepository memberRepository;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertThat(this.memberRepository).isNotNull();
		log.info("\t+ this.memberRepository: {}", this.memberRepository);
	} // beforeAll

	@Commit
//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("testSave")
	@Timeout(1L)
	void testSave() {
		log.trace("testSave() invoked.");

		Member member = new Member();
		member.setId("guest");
		member.setName("GUEST");
		member.setPassword("guest123");
		member.setRole(Role.ROLE_GUEST);
		member.setEnabled(true);
		log.info("\t+ member: {}", member);

		this.memberRepository.save(member);
	} // testSave

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(2)
	@DisplayName("testFindAll")
	@Timeout(1L)
	void testFindAll() {
		log.trace("testFindAll() invoked.");

		Iterable<Member> memberList = this.memberRepository.findAll();

		assertThat(memberList).isNotEmpty();
		memberList.forEach(log::info);
	} // testFindAll

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(3)
	@DisplayName("testFindById")
	@Timeout(1L)
	void testFindById() {
		log.trace("testFindById() invoked.");

//		Member member = this.memberRepository.findById("guest").orElse(null);
//		Member member = this.memberRepository.findById("member").orElse(null);
		Member member = this.memberRepository.findById("admin").orElse(null);

		assertThat(member).isNotNull();
		log.info("\t+ member: {}", member);

		assertThat(member.getBoardList().size()).isGreaterThan(0);
		member.getBoardList().forEach(log::info);
	} // testFindById

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(4)
	@DisplayName("testDelete")
	@Timeout(1L)
	void testDelete() {
		log.trace("testDelete() invoked.");

		Member foundMember = this.memberRepository.findById("guest").orElse(null);

		Objects.requireNonNull(foundMember);
		this.memberRepository.delete(foundMember);
	} // testFindById

} // end class
