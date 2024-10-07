package org.zerock.myapp.oauth2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zerock.myapp.entity.User;
import org.zerock.myapp.persistence.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Collections;


@Log4j2
@ToString

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Service
public class CustomOAuth2UserService

    /*
     * =======================================================
     * @FunctionalInterface
     * public interface `OAuth2UserService<R extends `OAuth2UserRequest`, U extends `OAuth2User`>`
     * =======================================================
     * Implementations of this interface are responsible for obtaining the user attributes of the End-User
     * (Resource Owner) from the `UserInfo` Endpoint using the Access Token granted to the Client
     * and returning an `AuthenticatedPrincipal` in the form of an `OAuth2User`.
     *
     * Type parameters:
     *      <R> – The type of OAuth 2.0 User Request
     *      <U> – The type of OAuth 2.0 User
     *
     * =======================================================
     * public class OAuth2UserRequest
     * =======================================================
     * Represents a request the `OAuth2UserService` uses when initiating a request to the `UserInfo` Endpoint.
     *
     * =======================================================
     * public interface OAuth2User extends OAuth2AuthenticatedPrincipal
     * =======================================================
     * A representation of a user `Principal` that is registered with an `OAuth 2.0 Provider`.
     *
     * An OAuth 2.0 user is composed of one or more attributes;
     * for example: first name, middle name, last name, email, phone number, address, etc.
     *
     * Each user attribute has a "name" and "value" and is keyed by the "name" in `getAttributes()`.
     *
     * NOTE: Attribute names are not standardized between providers and therefore will vary.
     *
     * Please consult the provider's API documentation for the set of supported user attribute names.
     * Implementation instances of this interface represent an `OAuth2AuthenticatedPrincipal`
     * which is associated to an `Authentication` object and may be accessed via `Authentication.getPrincipal()`.
     *
     */
    implements OAuth2UserService< OAuth2UserRequest, OAuth2User >, InitializingBean {

    @Autowired private UserRepository userRepository;
    @Autowired private HttpSession httpSession;


    /*
     * =======================================================
     * public OAuth2User CustomOAuth2UserService.loadUser(OAuth2UserRequest userRequest)
     *      throws OAuth2AuthenticationException
     * =======================================================
     * Returns an `OAuth2User` after obtaining the user attributes of the End-User from the `UserInfo` Endpoint.
     *
     * Specified by: loadUser in interface `OAuth2UserService`
     * Params: userRequest – the user request
     * Returns: an `OAuth2User`
     * Throws: `OAuth2AuthenticationException`
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.trace("loadUser({}) invoked.", userRequest);

        /*
         * =======================================================
         * public class DefaultOAuth2UserService
         *      implements OAuth2UserService<OAuth2UserRequest, OAuth2User>
         * =======================================================
         * An implementation of an `OAuth2UserService` that supports `standard OAuth 2.0 Provider's`.
         *
         * For `standard OAuth 2.0 Provider's`,
         * the attribute name used to access the user's name from the `UserInfo` response is required
         * and therefore must be available via `UserInfoEndpoint.getUserNameAttributeName()`.
         *
         * NOTE: Attribute names are not standardized between providers and therefore will vary.
         *
         * Please consult the provider's API documentation for the set of supported user attribute names.
         */
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        log.info("\t1. delegate: {}", delegate);

        /*
         * =======================================================
         * public OAuth2User DefaultOAuth2UserService.loadUser(OAuth2UserRequest userRequest)
         *      throws OAuth2AuthenticationException
         * =======================================================
         * Returns an `OAuth2User` after obtaining the user attributes of the End-User
         * from the `UserInfo` Endpoint.
         *
         * Specified by: loadUser in interface `OAuth2UserService`
         * Params:       userRequest – the user request
         * Returns:      an `OAuth2User`
         * Throws:      `OAuth2AuthenticationException`
         */
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        log.info("\t2. oAuth2User: {}", oAuth2User);

//        ---

        String registrationId =
            userRequest.

                /*
                 * =======================================================
                 * public ClientRegistration OAuth2UserRequest.getClientRegistration()
                 * =======================================================
                 * Returns the client registration.
                 *
                 * Returns:  `ClientRegistration`
                 */
                getClientRegistration().

                /*
                 * =======================================================
                 * public String ClientRegistration.getRegistrationId()
                 * =======================================================
                 * Returns the identifier for the registration.
                 *
                 * Returns:   the identifier for the registration.
                 */
                getRegistrationId();

        log.info("\t3. registrationId: {}", registrationId);

//        ---

        String userNameAttributeName =
            userRequest.

                /*
                 * =======================================================
                 *  public ClientRegistration OAuth2UserRequest.getClientRegistration()
                 * =======================================================
                 * Returns the client registration.
                 *
                 * Returns:  the `ClientRegistration`
                 */
                getClientRegistration().

                /*
                 * =======================================================
                 * public ClientRegistration.ProviderDetails ClientRegistration.getProviderDetails()
                 * =======================================================
                 * Returns the details of the provider.
                 *
                 * Returns:  the `ClientRegistration.ProviderDetails`
                 */
                getProviderDetails().

                /*
                 * =======================================================
                 * public ClientRegistration.ProviderDetails.UserInfoEndpoint
                 *      ClientRegistration.ProviderDetails.getUserInfoEndpoint()
                 * =======================================================
                 * Returns the details of the `UserInfo` Endpoint.
                 *
                 * Returns:
                 *      the `ClientRegistration.ProviderDetails.UserInfoEndpoint`
                 */
                getUserInfoEndpoint().

                /*
                 * =======================================================
                 * public String
                 *      ClientRegistration.ProviderDetails.UserInfoEndpoint.getUserNameAttributeName()
                 * =======================================================
                 * Returns the attribute name used to access the user's name from the user info response.
                 *
                 * Returns:
                 *      the attribute name used to access the user's name from the user info response.
                 *
                 */
                getUserNameAttributeName();

        log.info("\t4. userNameAttributeName: {}", userNameAttributeName);

//        ---

        OAuthAttributes attributes =
                    OAuthAttributes.of(
                        registrationId,
                        userNameAttributeName,

                        /*
                         * =======================================================
                         * public abstract Map<String, Object> OAuth2AuthenticatedPrincipal.getAttributes()
                         * =======================================================
                         * Get the OAuth 2.0 token attributes
                         *
                         * Returns:   the OAuth 2.0 token attributes
                         */
                        oAuth2User.getAttributes()
                    );

        log.info("\t5. attributes: {}", attributes);

//        ---

        User user = saveOrUpdate(attributes);
        log.info("\t6. user: {}", user);

//        ---

        httpSession.setAttribute("user", new SessionUser(user));

//        ---

        /*
         * =======================================================
         * DefaultOAuth2User
         * =======================================================
         * The default implementation of an `OAuth2User`.
         *
         * User attribute names are not standardized between providers
         * and therefore it is required to supply the key for the user's "name" attribute
         * to one of the constructors.
         *
         * The key will be used for accessing the "name" of the `Principal` (user) via `getAttributes()`
         * and returning it from `getName()`.
         */

        /*
         * =======================================================
         * public DefaultOAuth2User(
         *     @Nullable  Collection<? extends org.springframework.security.core.GrantedAuthority> authorities,
         *     @NotNull Map<String, Object> attributes,
         *     String nameAttributeKey
         * )
         * =======================================================
         * Constructs a `DefaultOAuth2User` using the provided parameters.
         *
         * Params:
         *      authorities         – the authorities granted to the user attributes.
         *      attributes          – the attributes about the user.
         *      nameAttributeKey    – the key used to access the user's "name" from `getAttributes()`.
         */
        return new DefaultOAuth2User(

                /*
                 * =======================================================
                 * public static <T> Set<SimpleGrantedAuthority>
                 *      Collections.singleton(SimpleGrantedAuthority o)
                 * =======================================================
                 * Returns an immutable set containing only the specified object.
                 * The returned set is serializable.
                 *
                 * Params:
                 *      o – the sole object to be stored in the returned set.
                 * Returns:
                 *      an immutable set containing only the specified object.
                 */
                Collections.singleton(

                    /*
                     * =======================================================
                     * org.springframework.security.core.authority.SimpleGrantedAuthority
                     *
                     * public SimpleGrantedAuthority(String role)
                     * =======================================================
                     * Basic concrete implementation of a `GrantedAuthority`.
                     *
                     * Stores a String representation of an authority granted to the
                     * `org.springframework.security.core.Authentication` object.
                     */
                    new SimpleGrantedAuthority( user.getRoleKey() )

                ),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
            );

    } // loadUser


    private User saveOrUpdate(OAuthAttributes attributes) {
        log.trace("saveOrUpdate({}) invoked.", attributes);

        User user =
            this.userRepository.
                findByEmail( attributes.getEmail() ).   // returns `Optional<User>`

                /*
                 * =======================================================
                 * public <U> Optional<User>
                 *     Optional.map(Function<? super User, ? extends User> mapper)
                 * =======================================================
                 * If a value is present, returns an `Optional` describing (as if by ofNullable) the result of
                 * applying the given mapping function to the value, otherwise returns an empty `Optional`.
                 *
                 * If the mapping function returns a null result, then this method returns an empty `Optional`.
                 *
                 * Params:
                 *      mapper – the mapping function to apply to a value, if present
                 * Returns:
                 *      an `Optional` describing the result of
                 *      applying a mapping function to the value of this `Optional`,
                 *      if a value is present, otherwise an empty `Optional`
                 * Throws:
                 *      `NullPointerException` – if the mapping function is null
                 *
                 * API Note:
                 *      This method supports post-processing on Optional values,
                 *      without the need to explicitly check for a return status.
                 *
                 *      For example, the following code traverses a stream of URIs,
                 *      selects one that has not yet been processed,
                 *      and creates a path from that URI, returning an `Optional<Path>` :
                 *
                 *      Optional<Path> p =
                 *              uris.stream().filter(uri -> !isProcessedYet(uri)).findFirst().map(Paths::get);
                 *
                 *      Here, findFirst returns an `Optional<URI>`,
                 *      and then map returns an `Optional<Path>` for the desired URI if one exists.
                 */
                map( entity -> entity.update( attributes.getName(), attributes.getPicture() ) ).

                /*
                 * =======================================================
                 * public User Optional.orElse(User other)
                 * =======================================================
                 * If a value is present, returns the value, otherwise returns other.
                 *
                 * Params:  other – the value to be returned, if no value is present. May be null.
                 * Returns: the value, if present, otherwise other
                 */
                orElse( attributes.toEntity() );

        log.info("\t+ user: {}", user);

        /*
         * =======================================================
         * public abstract <S extends T> User
         *      CrudRepository.save(User entity)
         * =======================================================
         * Saves a given entity.
         *
         * Use the returned instance for further operations
         * as the save operation might have changed the entity instance completely.
         *
         * Params:   entity – must not be null.
         * Returns:  the saved entity; will never be null.
         * Throws:
         *   `IllegalArgumentException` – in case the given entity is null.
         *   `OptimisticLockingFailureException` –
         *       when the entity uses optimistic locking and has a version attribute
         *       with a different value from that found in the persistence store.
         *       Also thrown if the entity is assumed to be present but does not exist in the database.
         */
        return this.userRepository.save(user);
    } // saveOrUpdate


    @Override
    public void afterPropertiesSet() throws Exception {
        log.trace("afterPropertiesSet() invoked.");

        log.info("\t+ this.userRepository: {}", this.userRepository);
        log.info("\t+ this.httpSession: {}", this.httpSession);
    } // afterPropertiesSet

} // end class
