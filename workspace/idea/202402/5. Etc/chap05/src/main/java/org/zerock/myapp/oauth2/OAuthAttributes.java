package org.zerock.myapp.oauth2;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.zerock.myapp.domain.Role;
import org.zerock.myapp.domain.User;

import java.util.Map;


@Log4j2
@ToString
@Getter
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String picture;


    @Builder
    private OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        log.trace("Constructor({}, {}, {}, {}, {}) invoked.", attributes, nameAttributeKey, name, email, picture);

        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    } // Constructor

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        log.trace("of(registrationId: {}, userNameAttributeName: {}, attributes: {}) invoked.",
                    registrationId, userNameAttributeName, attributes);

        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        } // if

        return ofGoogle(userNameAttributeName, attributes);
    } // of

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        log.trace("ofGoogle(userNameAttributeName: {}, attributes: {}) invoked.", userNameAttributeName, attributes);

        return
            OAuthAttributes.builder().
                name((String) attributes.get("name")).
                email((String) attributes.get("email")).
                picture((String) attributes.get("picture")).
                attributes(attributes).
                nameAttributeKey(userNameAttributeName).
                build();
    } // ofGoogle

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        log.trace("ofNaver(userNameAttributeName: {}, attributes: {}) invoked.", userNameAttributeName, attributes);

        Map<String, Object> response = ( Map<String, Object> ) attributes.get("response");
        log.info("\t+ response: {}", response);

        return OAuthAttributes.builder().
                    name( (String) response.get("name") ).
                    email( (String) response.get("email") ).
                    picture( (String) response.get("profile_image") ).
                    attributes( response ).
                    nameAttributeKey( userNameAttributeName ).
                    build();
    } // ofNaver

    public User toEntity() {
        log.trace("toEntity() invoked.");

        return User.builder().
                    name(name).
                    email(email).
                    picture(picture).
                    role(Role.GUEST).
                    build();
    } // toEntity

} // end class
