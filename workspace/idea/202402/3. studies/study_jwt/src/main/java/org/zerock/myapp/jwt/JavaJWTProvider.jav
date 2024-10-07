package org.zerock.myapp.jwt;

import com.auth0.jwt.HeaderParams;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zerock.myapp.domain.User;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Log4j2
@NoArgsConstructor

@Component
public final class JavaJWTProvider implements InitializingBean {
    @Autowired
    private JWTProperties jwtProperties;


    public String generateToken(User user, Duration expiredAt) throws NoSuchAlgorithmException {
        log.trace("generateToken({}, {}) invoked.", user, expiredAt);

        return createToken(user, new Date(new Date().getTime() + expiredAt.toMillis()));
    } // generateToken

    private String createToken(User user, Date expiry) throws NoSuchAlgorithmException {
        log.trace("createToken({}, {}) invoked.", user, expiry);

        /* =======================================================
         * JWT Structure :   <Header>.<Payload>.<Digital Signature>
         * =======================================================
         * 1. Header
         *    - ALGORITHM(alg):     The algorithm used to sign a JWT.
         *    - CONTENT_TYPE(cty):  The content type of the JWT.
         *    - TYPE(typ):          The media type of the JWT.
         *    - KEY_ID(kid):        The key ID of a JWT used to specify the key for signature validation.
         *
         * 2. Payload
         *    (1) Registered Claims The information of the JWT
         *      - ISSUER(iss)       The "iss" (issuer) claim identifies the principal that issued the JWT.
         *      - SUBJECT(sub)      The "sub" (subject) claim identifies the principal that is the subject of the JWT.
         *      - AUDIENCE(aud)     The "aud" (audience) claim identifies the recipients that the JWT is intended for.
         *      - EXPIRES_AT(exp)   The "exp" (expiration time) claim identifies the expiration time on or
         *                          after which the JWT MUST NOT be accepted for processing.
         *      - NOT_BEFORE(nbf)   The "nbf" (not before) claim identifies the time before
         *                          which the JWT MUST NOT be accepted for processing.
         *      - ISSUED_AT(iat)    The "iat" (issued at) claim identifies the time at which the JWT was issued.
         *      - JWT_ID(jti)       The "jti" (JWT ID) claim provides a unique identifier for the JWT.
         *    (2) Public Claims     The exposed information to the public. In general, URI format.
         *    (3) Private Claims    The secure information used for communication between client and server.
         *
         * 3. Digital Signature
         */

        /*
            -------------------
            Create a JWT
            -------------------
            Use `JWT.create()`, configure the claims, and then call `sign(algorithm)` to sign the JWT.
            The example below demonstrates this using the `RS256` signing algorithm:

                try {
                    Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
                    String token = JWT.create().withIssuer("auth0").sign(algorithm);
                } catch (JWTCreationException exception){
                    // Invalid Signing configuration / Couldn't convert Claims.
                } // try-catch
        */

        Map<String, Object> header = new HashMap<>();
        header.put(HeaderParams.TYPE, "JWT");
        header.put(HeaderParams.ALGORITHM, "HS256");

        header.put(HeaderParams.KEY_ID, this.jwtProperties.getSecretKey());
        // withKeyId(this.jwtProperties.getSecretKey()) // Ditto.

        /* If RSA256,
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair pair = generator.generateKeyPair();

        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        Algorithm rsa256 = Algorithm.RSA256((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
        */

        return JWT.create().
                    // 1. Header
                    withHeader(header).

                    // 2. Payload (Registered Claims)
                    withIssuer(this.jwtProperties.getIssuer()).
                    withIssuedAt(Instant.now()).
                    withExpiresAt(expiry).
                    withSubject(user.getEmail()).
                    withAudience(user.getUsername()).

                    // 3. Digital Signature
                    //sign(rsa256);           // Signing Algorithm: SHA256withRSA
                    sign(Algorithm.HMAC256(this.jwtProperties.getSecretKey()));  // Signing Algorithm: HmacSHA256
    } // createToken


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");
        log.info("\t+ this.jwtProperties: {}", this.jwtProperties);
    } // afterPropertiesSet

} // end class
