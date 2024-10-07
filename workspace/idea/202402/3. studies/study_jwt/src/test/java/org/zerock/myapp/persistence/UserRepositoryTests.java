package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.myapp.domain.User;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
public class UserRepositoryTests {
    @Autowired private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertThat(this.userRepository).isNotNull();
        log.info("\t+ this.userRepository: {}", this.userRepository);

        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("\t+ this.passwordEncoder: {}", this.passwordEncoder);
    } // beforeAll

//    @Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testInsertJWTAllUsers")
    @Timeout(1L)
    void testInsertJWTAllUsers() {
        log.trace("testInsertJWTAllUsers() invoked.");

        this.userRepository.deleteAll();

        for (int i=1; i<=3; i++) {
            User user = new User();
            user.setUsername("Yoseph" + i);
            user.setPassword(this.passwordEncoder.encode("0123456789"));
            user.setEmail("trinity"+i+"@naver.com");

            this.userRepository.save(user);
            log.info("\t+ user: {}", user);
        } // for
    } // testInsertJWTAllUsers

    //    @Disabled
    @Tag("fast")
    @Test
    @Order(2)
    @DisplayName("testFindByUsername")
    @Timeout(1L)
    void testFindByUsername() {
        log.trace("testFindByUsername() invoked.");

        User foundUser = this.userRepository.findByUsername("Yoseph1");
        log.info("\t+ foundUser: {}", foundUser);
    } // testFindByUsername





} // end class
