package org.zerock.myapp.jwt;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.User;
import org.zerock.myapp.persistence.UserRepository;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class JJWTProviderTests {
    @Autowired private MockMvc mockMvc;
    @Autowired private JJWTProvider jjwtProvider;
    @Autowired private JWTProperties jwtProperties;
    @Autowired private UserRepository userRepository;

    private String secretKey;
    private String token;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assert this.mockMvc != null;
        log.info("\t+ this.mockMvc: {}", this.mockMvc);

        assert this.jjwtProvider != null;
        log.info("\t+ this.jjwtProvider: {}", this.jjwtProvider);

        assertNotNull(this.userRepository);
        log.info("\t+ this.UserRepository: {}", this.userRepository);

        assertThat(this.jwtProperties).isNotNull();
        log.info("\t+ this.jwtProperties: {}", this.jwtProperties);
    } // beforeAll

    @BeforeEach
    void beforeEach() {
        log.trace("beforeEach() invoked.");

        this.secretKey = this.jwtProperties.getSecretKey();
        log.info("\t+ this.secretKey: {}", this.secretKey);

        User user = this.userRepository.findById(3L).orElse(null);
        assertThat(user).isNotNull();
        log.info("\t+ user: {}", user);

//        ---

        this.token = this.jjwtProvider.generateToken(user, Duration.ofDays(1000));
        assertThat(this.token).isNotNull();
        log.info("\t+ this.token: {}", this.token);
    } // beforeEach

    // @Disabled
    @Test
    @Order(1)
    @DisplayName("testIsValidJwsToken")
    @Timeout(1L)
    void testIsValidJwsToken() {
        log.trace("testIsValidJwsToken() invoked.");

        boolean isValid = this.jjwtProvider.isValidToken(this.token, this.secretKey);

        assertThat(isValid).isTrue();
        log.info("\t+ isValid: {}", isValid);
    } // testIsValidJwsToken

    // @Disabled
    @Test
    @Order(2)
    @DisplayName("testParseJwsToken")
    @Timeout(1L)
    void testParseJwsToken() {
        log.trace("testParseJwsToken() invoked.");

        var jws = this.jjwtProvider.parseJwsToken(this.token, this.secretKey);

        assertThat(jws).isNotNull();
        log.info("\t+ jws: {}", jws);
        log.info("\t\t- header: {}", jws.getHeader());
        log.info("\t\t- body: {}", jws.getBody());
    } // testParseJwsToken

    // @Disabled
    @Test
    @Order(3)
    @DisplayName("testGetAuthenticationFromJws")
    @Timeout(1L)
    void testGetAuthenticationFromJws() {
        log.trace("testGetAuthenticationFromJWT() invoked.");

        Authentication auth = this.jjwtProvider.getAuthentication(this.token, this.secretKey);

        assertThat(auth).isNotNull();
        log.info("\t+ auth: {}", auth);
    } // testGetAuthenticationFromJws

    // @Disabled
    @Test
    @Order(4)
    @DisplayName("testGetUserId")
    @Timeout(1L)
    void testGetUserId() {
        log.trace("testGetUserId() invoked.");

        Long userId = this.jjwtProvider.getUserId(this.token, this.secretKey);

        assertThat(userId).isEqualTo(3L);
        log.info("\t+ userId: {}", userId);
    } // testGetUserId

} // end class
