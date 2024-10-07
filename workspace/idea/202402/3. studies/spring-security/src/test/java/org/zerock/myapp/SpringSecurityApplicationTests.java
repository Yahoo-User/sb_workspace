package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.domain.Role;
import org.zerock.myapp.persistence.MemberRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class SpringSecurityApplicationTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertNotNull(this.memberRepository);
        assert this.passwordEncoder != null;

        log.info("\t+ this.memberRepository: {}", this.memberRepository);
        log.info("\t+ this.passwordEncoder: {}", this.passwordEncoder);
    } // beforeAll


//    @Disabled
	@Test
    @Order(1)
    @DisplayName("testInsert")
    @Timeout(3L)
	void testInsert() {
        log.trace("testInsert() invoked.");

        Member member = new Member();
        member.setId("member");
        member.setPassword(this.passwordEncoder.encode("member123"));
        member.setName("MEMBER");
        member.setRole(Role.ROLE_MEMBER);
        member.setEnabled(1);
        log.info("\t+ member: {}", member);

        this.memberRepository.save(member);

        Member manager = new Member();
        manager.setId("manager");
        manager.setPassword(this.passwordEncoder.encode("manager123"));
        manager.setName("MANAGER");
        manager.setRole(Role.ROLE_MANAGER);
        manager.setEnabled(1);
        log.info("\t+ manager: {}", manager);

        this.memberRepository.save(manager);

        Member admin = new Member();
        admin.setId("admin");
        admin.setPassword(this.passwordEncoder.encode("admin123"));
        admin.setName("ADMIN");
        admin.setRole(Role.ROLE_ADMIN);
        admin.setEnabled(1);
        log.info("\t+ admin: {}", admin);

        this.memberRepository.save(admin);

	} // testInsert

} // end class
