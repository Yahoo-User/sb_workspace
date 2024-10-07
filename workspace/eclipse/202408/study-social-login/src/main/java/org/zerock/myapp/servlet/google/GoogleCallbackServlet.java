package org.zerock.myapp.servlet.google;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@Slf4j

@NoArgsConstructor

@WebServlet("/login/oauth2/code/google")
public class GoogleCallbackServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	/**
	 * -----------------------------
	 * GoogleCredential	(*Deprecated)
	 * -----------------------------
	 * Please use `google-auth-library` for handling Application Default Credentials and other non-OAuth2 based authentication.	(***)
	 * Thread-safe Google-specific implementation of the OAuth 2.0 helper for accessing protected resources using an access token, 
	 * as well as optionally refreshing the access token when it expires using a refresh token.
	 * 
	 * There are three modes supported: 
	 * 		(1) access token only
	 * 		(2) refresh token flow, and 
	 * 		(3) service account flow (with or without impersonating a user). 
	 * 
	 * If all you have is an `access token`, you simply pass the `TokenResponse` to the credential using `Builder.setFromTokenResponse(TokenResponse)`.
	 * Google credential uses `BearerToken.authorizationHeaderAccessMethod()` as the access method.
	 * 
	 * Sample usage 1 - (1) access token only : 

			 public static GoogleCredential createCredentialWithAccessTokenOnly(TokenResponse tokenResponse) {
			   		return new GoogleCredential().setFromTokenResponse(tokenResponse);
			 } // createCredentialWithAccessTokenOnly

	 * If you have a refresh token, it is similar to the case of access token only, 
	 * but you additionally need to pass the credential the client secrets 
	 * using `Builder.setClientSecrets(GoogleClientSecrets)` or `Builder.setClientSecrets(String, String)`.
	 * 
	 * Google credential uses `GoogleOAuthConstants.TOKEN_SERVER_URL` as the token server URL,
	 * and `ClientParametersAuthentication` with the client ID and secret as the client authentication.
	 * 
	 * Sample usage 2 - (2) refresh token flow: 

			 public static GoogleCredential createCredentialWithRefreshToken(
			 			HttpTransport transport, JsonFactory jsonFactory, GoogleClientSecrets clientSecrets, TokenResponse tokenResponse
			 		) {
					  return new GoogleCredential.Builder()
			  						.setTransport(transport)
			                        .setJsonFactory(jsonFactory)
			                        .setClientSecrets(clientSecrets)
			                        .build()
			                        .setFromTokenResponse(tokenResponse);
			 } // createCredentialWithRefreshToken

	 * The service account flow is used when you want to access data owned by your client application.
	 * You download the private key in a `.p12` file from the Google APIs Console.
	 * Use `Builder.setServiceAccountId(String)`, `Builder.setServiceAccountPrivateKeyFromP12File(File)`, and `Builder.setServiceAccountScopes(Collection)`.
	 * 
	 * Sample usage 3 - (3) service account flow (without impersonating a user): 

			 public static GoogleCredential createCredentialForServiceAccount(
			 		HttpTransport transport, JsonFactory jsonFactory, String serviceAccountId, Collection&lt;String&gt; serviceAccountScopes, File p12File
			 	) throws GeneralSecurityException, IOException {
					   return new GoogleCredential.Builder()
					   				.setTransport(transport)
					   				.setJsonFactory(jsonFactory)
					       			.setServiceAccountId(serviceAccountId)
					       			.setServiceAccountScopes(serviceAccountScopes)
					       			.setServiceAccountPrivateKeyFromP12File(p12File)
					       			.build();
			 } // createCredentialForServiceAccount

	 * You can also use the service account flow to impersonate a user in a domain that you own.
	 * This is very similar to the service account flow above, but you additionally call `Builder.setServiceAccountUser(String)`.
	 * 
	 * Sample usage 4 - (3) service account flow (with impersonating a user): 

			 public static GoogleCredential createCredentialForServiceAccountImpersonateUser(
			 		HttpTransport transport, JsonFactory jsonFactory, String serviceAccountId, Collection<String> serviceAccountScopes, File p12File, String serviceAccountUser
			 	) throws GeneralSecurityException, IOException {
					   return new GoogleCredential.Builder()
					   				.setTransport(transport)
					       			.setJsonFactory(jsonFactory)
					       			.setServiceAccountId(serviceAccountId)
					       			.setServiceAccountScopes(serviceAccountScopes)
					       			.setServiceAccountPrivateKeyFromP12File(p12File)
					       			.setServiceAccountUser(serviceAccountUser)
					       			.build();
			 } // createCredentialForServiceAccountImpersonateUser

	 * If you need to persist the access token in a data store, use `DataStoreFactory` and `Builder.addRefreshListener(CredentialRefreshListener)` with `DataStoreCredentialRefreshListener`. 
	 * 
	 * If you have a custom request initializer, request execute interceptor, or unsuccessful response handler, 
	 * take a look at the sample usage for `HttpExecuteInterceptor` and `HttpUnsuccessfulResponseHandler`, which are interfaces that this class also implements.
	 * 
	 * 		@Since:1.7
	 */
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			log.trace("service(req, res) invoked.");
			
			try {
					Map<String, String[]> paramMap = req.getParameterMap();
					paramMap.forEach((k, v) -> log.info("\t+ param: {}({})", k, v));
					
					// ------------------------------
					// Step1. Get Request Parameter, named "code".
					// ------------------------------
					String code = req.getParameter("code");
					log.info("\t+ Step1 - code: {}", code);
	
					// ------------------------------
					// Step2. Ask for Access Token with Code gotten from Step1.
					// ------------------------------
					Objects.requireNonNull(code);
					
					HttpTransport transport = new NetHttpTransport();
					JsonFactory factory = new GsonFactory();
					
					log.info("\t+ Step2 - transport: {}, factory: {}", transport, factory);

					  /**
					   * @param transport 		HTTP transport
					   * @param jsonFactory 	JSON factory
					   * @param clientId 			client identifier issued to the client during the registration process
					   * @param clientSecret 	client secret
					   * @param code 				authorization code generated by the authorization server
					   * @param redirectUri 	redirect URL parameter matching the redirect URL parameter in the authorization request
					   */
					AuthorizationCodeTokenRequest tokenRequest = 
							new GoogleAuthorizationCodeTokenRequest(
									transport,
									factory, 
									GoogleOAuth2Utils.CLIENT_ID, 
									GoogleOAuth2Utils.CLIENT_SECRET, 
									code, 
									GoogleOAuth2Utils.REDIRECT_URI);
					
					log.info("\t+ Step2 - tokenRequest: {}", tokenRequest);
					
					Objects.requireNonNull(tokenRequest);
					TokenResponse tokenResponse = tokenRequest.execute();
					log.info("\t+ Step2 - tokenResponse: {}", tokenResponse);
					
					// ------------------------------
					// Step3. Get OAuth2 Tokens with Token Response gotten From Step2.
					// ------------------------------
					Objects.requireNonNull(tokenResponse);
					
					String tokenType 		= tokenResponse.getTokenType();
					String accessToken 	= tokenResponse.getAccessToken();
					String refreshToken 	= tokenResponse.getRefreshToken();
					
					log.info("\t+ Step3 - tokenType({}), accessToken({}), refreshToken({})", tokenType, accessToken, refreshToken);
					
					// ------------------------------
					// Step4. Ask for Credential with Access Token gotten from Step3.
					// ------------------------------
	//				GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);		// 1st. method
					GoogleCredential credential = createCredentialWithAccessTokenOnly(tokenResponse);		// 2nd. method
					
					log.info("\t+ Step4 - credential: {}", credential);
									
					// ------------------------------
					// Step5. Get User Information from OAuth v2 with Credential gotten from Step4.
					// ------------------------------
					Objects.requireNonNull(credential);
					
					Oauth2 oauth2 = 
							new Oauth2.Builder(transport, factory, credential)
											   .setApplicationName(GoogleOAuth2Utils.CLIENT_NAME)
											   .build();
					
					log.info("\t+ Step5 - oauth2: {}", oauth2);
					
					Objects.requireNonNull(oauth2);
					Userinfo userInfo = oauth2.userinfo().get().execute();
					log.info("\t+ Step5 - userInfo: {}", userInfo);
					
					Objects.requireNonNull(userInfo);
					log.info("\t+ Step5 - JSON: {}", userInfo.getFactory().toString());
	
					// ------------------------------
					// Step6. Response Processing.
					// ------------------------------
					res.setCharacterEncoding("utf8");
					res.setContentType("text/html; charset=utf8");
					
					@Cleanup PrintWriter out = res.getWriter();
					
					out.println("<ol>");
					out.println("		<li>%s</li>".formatted(userInfo.getId()));
					out.println("		<li>%s</li>".formatted(userInfo.getHd()));
					out.println("		<li>%s</li>".formatted(userInfo.getGender()));
					out.println("		<li>%s</li>".formatted(userInfo.getGivenName()));
					out.println("		<li>%s</li>".formatted(userInfo.getFamilyName()));
					out.println("		<li>%s</li>".formatted(userInfo.getName()));
					out.println("		<li>%s</li>".formatted(userInfo.getEmail()));
					out.println("		<li>%s</li>".formatted(userInfo.getVerifiedEmail()));
					out.println("		<li>%s</li>".formatted(userInfo.getLocale()));
					out.println("		<li>%s</li>".formatted(userInfo.getPicture()));
					out.println("		<li>%s</li>".formatted(userInfo.getUnknownKeys()));
					out.println("</ol>");
					
					out.flush();
			} catch(Exception e) {
				throw new IOException(e);
			} // try-catch
	} // service
	
	 private GoogleCredential createCredentialWithAccessTokenOnly(TokenResponse tokenResponse) {
		 	log.trace("createCredentialWithAccessTokenOnly({}) invoked.", tokenResponse);
		 	
	   		return new GoogleCredential().setFromTokenResponse(tokenResponse);
	 } // createCredentialWithAccessTokenOnly

} // end class

