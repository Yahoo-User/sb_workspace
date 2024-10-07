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

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@SpringBootTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PasswordEncoderTests {
    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        Objects.requireNonNull(this.memberRepo);
        assertNotNull(this.passwordEncoder);

        log.info("\t+ this.memberRepo: {}", this.memberRepo);
        log.info("\t+ this.passwordEncoder: {}", this.passwordEncoder);
    } // beforeAll


//    @Enabled
    @Test
    @Order(1)
    @DisplayName("testInsert")
    @Timeout(1L)
    void testInsert() {
        log.trace("testInsert() invoked.");

        Member member = new Member();
        member.setId("member3");
        member.setPassword(this.passwordEncoder.encode("member333"));
        member.setName("Yoseph");
        member.setRole(Role.ROLE_ADMIN);
        member.setEnabled(true);

        this.memberRepo.save(member);
    } // testInsert

} // end class
