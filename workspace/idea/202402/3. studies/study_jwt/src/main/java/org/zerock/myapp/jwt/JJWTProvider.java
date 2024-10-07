package org.zerock.myapp.jwt;

import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.zerock.myapp.domain.User;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;


@Log4j2
@NoArgsConstructor

@Component
public final class JJWTProvider implements InitializingBean {
    @Autowired private JWTProperties jwtProperties;


    public String generateToken(User user, Duration expiredAt) {
        log.trace("generateToken({}, {}) invoked.", user, expiredAt);

        long expiredTime = new Date().getTime() + expiredAt.toMillis();
        return createToken(
                user,
                new Date(expiredTime),
                this.jwtProperties.getSecretKey(),
                this.jwtProperties.getIssuer()
        );
    } // generateToken

    private String createToken(User user, Date expiry, String secretKey, String issuer) {
        log.trace("createToken({}, {}, {}, {}) invoked.", user, expiry, secretKey, issuer);

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

        // ----------------------
        // Instead, use this :
        // ----------------------
        //                                    ****  *********************
        // byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        // Key key = Keys.hmacShaKeyFor(keyBytes);
        // jwtBuilder.signWith(key);    // or signWith(Key, SignatureAlgorithm)
        // ----------------------

        return Jwts.builder().
                    // ------------------
                    // 1. Header
                    // ------------------
                    setHeaderParam(Header.TYPE, Header.JWT_TYPE).   // typ

                    // ------------------
                    // 2. Payload (Registered Claims)
                    // ------------------
                    setIssuer(issuer).                  // iss
                    setIssuedAt(new Date()).            // iat
                    setExpiration(expiry).              // exp
                    setSubject(user.getEmail()).        // sub
                    setAudience(user.getUsername()).    // aud

                    // ------------------
                    // 3. Payload (Private Claims)
                    // ------------------
                    // Sets a custom JWT Claims parameter value.
                    claim("id", user.getId()).          // id - User Id
                    claim("pw", user.getPassword()).    // pw - User Password

                    // ------------------
                    // 4. Digital Signature.
                    // ------------------
                    // 4-1. In the `jjwt-0.9.1`     (***)
                    // Signing Algorithm: HmacSHA256
                    signWith(SignatureAlgorithm.HS256, secretKey).


                    // 4-2. In the `jjwt-0.11.5`    (***)
                    // Signing Algorithm: HmacSHA256
                    // signWith( this.getHmacShaKey(secretKey), SignatureAlgorithm.HS256 ).

                    // ------------------
                    // 5. JWT Compact Serialization
                    // ------------------
                    // Actually builds the JWT and serializes it to a compact, URL-safe string
                    // according to the JWT Compact Serialization rules.
                    compact();
    } // createToken

    //------------------------------------
    // Required in the `jjwt-0.11.5`
    //------------------------------------
//    public boolean isValidToken(String token, String secretKey) {
//        log.trace("isValidToken({}, {}) invoked.", token, secretKey);
//
//        try {
//            Jwt<Header, Claims> jwt = this.parseToken(token, secretKey);
//
//            return jwt != null;
//        } catch (Exception e) {
//            return false;
//        } // try-catch
//    } // isValidToken

    // ------------------------
    // Required in the `jjwt-0.9.1`
    // ------------------------
    public boolean isValidToken(String token, String secretKey) {
        log.trace("isValidToken({}, {}) invoked.", token, secretKey);

        try {
            var jws = this.parseJwsToken(token, secretKey);
            return jws != null;
        } catch (Exception e) {
            return false;
        } // try-catch
    } // isValidToken

    //------------------------------------
    // Required in the `jjwt-0.11.5`
    //------------------------------------
//    private SecretKey getHmacShaKey(String secretKey) throws WeakKeyException, IllegalArgumentException {
//        log.trace("getHmacShaKey({}) invoked.", secretKey);
//
//        if(secretKey == null || secretKey.length() < 32) {
//            throw new IllegalArgumentException("secretKey must be 32 length and above.");
//        } // if
//
//        // Creates a new SecretKey instance for use with HMAC-SHA algorithms based on the specified key byte array.
//        return Keys.hmacShaKeyFor(secretKey.getBytes());
//    } // getHmacShaKey

    // ------------------------
    // Required in the `jjwt-0.11.5`
    // ------------------------
//    Jwt<Header, Claims> parseJwsToken(String token, String secretKey) {
//        log.trace("parseToken({}, {}) invoked.", token, secretKey);
//
//         JwtParser jwtParser =
//                 Jwts.parserBuilder().
//                         setSigningKey(this.getHmacShaKey(token)).
//                         build();
//
//         return jwtParser.parseClaimsJws(token);
//    } // parseJwsToken

    // ------------------------
    // Required in the `jjwt-0.9.1`
    // ------------------------
     Jws<Claims> parseJwsToken(String token, String secretKey) {
        log.trace("parseToken({}, {}) invoked.", token, secretKey);

        return Jwts.parser().
                    setSigningKey(secretKey).
                    parseClaimsJws(token);
    } // parseJwsToken

    public Authentication getAuthentication(String token, String secretKey) {
        log.trace("getAuthentication({}, {}) invoked.", token, secretKey);

        var jws = this.parseJwsToken(token, secretKey);

        Claims claims = jws.getBody();
        String username = claims.getAudience();
        String password = claims.get("pw", String.class);

        Set<SimpleGrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(username, password, authorities),
                token, authorities
        );
    } // getAuthentication

    public Long getUserId(String token, String secretKey) {
        log.trace("getUserId({}) invoked.", token);

        Claims claims = this.parseJwsToken(token, secretKey).getBody();
        return claims.get("id", Long.class);
    } // getUserId


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");
        log.info("\t+ this.jwtProperties: {}", this.jwtProperties);
    } // afterPropertiesSet

} // end class
