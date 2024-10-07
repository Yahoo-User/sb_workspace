package org.zerock.myapp.jwt;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.*;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class KeyPairGeneratorTests {


//    @Disabled
    @Test
    @Order(1)
    @DisplayName("testKeyPairGenerator")
    @Timeout(1L)
    void testKeyPairGenerator() throws NoSuchAlgorithmException {
        log.trace("testKeyPairGenerator() invoked.");

        // ------------------------
        // Required in the `jjwt-0.9.1` and in the `jjwt-0.11.5`
        // ------------------------
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        log.info("\t+ keyPairGenerator: {}", keyPairGenerator);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        log.info("\t+ keyPair: {}", keyPair);

        PublicKey rsaPublicKey = keyPair.getPublic();
        PrivateKey rsaPrivateKey = keyPair.getPrivate();

        log.info("\t+ rsaPublic: {}, rsaPrivate: {}", rsaPublicKey, rsaPrivateKey);
    } // testKeyPairGenerator


//    @Disabled
    @Test
    @Order(2)
    @DisplayName("testHmacSHA256")
    @Timeout(1L)
    void testHmacSHA256() {
        log.trace("testHmacSHA256() invoked.");

        // ------------------------
        // Required in the `jjwt-0.11.5`
        // ------------------------
        // ** NOTE ** : Secret Key Length Must Be 32 Length and Above.
//        String secretKeyStr = "12345678901234567890123456789012";
//        byte[] secretKeyBytes = secretKeyStr.getBytes();
//
//        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
//        log.info("\t+ secretKey: {}", secretKey);
//        log.info("\t+ Algorithm: {}, Format: {}, secretKeyStr: {}",
//                secretKey.getAlgorithm(), secretKey.getFormat(), new String(secretKey.getEncoded()));
    } // testHmacSHA256


} // end class
