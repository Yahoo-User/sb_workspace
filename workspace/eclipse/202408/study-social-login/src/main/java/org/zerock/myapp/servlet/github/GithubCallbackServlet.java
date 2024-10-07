package org.zerock.myapp.servlet.github;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.scribejava.apis.GitHubApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@Slf4j

@NoArgsConstructor

@WebServlet("/login/oauth2/code/github")
public class GithubCallbackServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	private OAuth20Service oauthService;
	

	/**
	 * ===================
	 * 	DefaultApi20
	 * ===================
	 * 	Default implementation of the OAuth protocol, version 2.0.
	 * 
	 * 	This abstract class is meant to be extended by concrete implementations of the API, providing the endpoints and endpoint-http-verbs.
	 * If your API adheres to the 2.0 protocol correctly, you just need to extend this class and define the getters for your endpoints.
	 * If your API does something a bit different, you can override the different extractors or services, in order to fine-tune the process.
	 *
	 *	--------------------------------
	 *	GitHubApi extends DefaultApi20
	 *	--------------------------------
	 */
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");

		// --------------
		this.oauthService = 
				new ServiceBuilder(GithubOAuth2Utils.CLIENT_ID)
					.apiSecret(GithubOAuth2Utils.CLIENT_SECRET)
					.callback(GithubOAuth2Utils.REDIRECT_URI)
					.defaultScope(GithubOAuth2Utils.DEFAULT_SCOPE)	// Optional, specify scope if needed
					.build(GitHubApi.instance());

		// --------------
		Objects.requireNonNull(this.oauthService);
		log.info("\t+ this.oauthService: {}", this.oauthService);
	} // postConstruct
	
	
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
					// Step2. Get a Access Token with Code gotten from Step1.
					// ------------------------------
					Objects.requireNonNull(code);
					
					OAuth2AccessToken accessToken = this.oauthService.getAccessToken(code);
					log.info("\t+ Step2 - accessToken: {}", accessToken);
					
					// ------------------------------
					// Step3. Create a New OAuth2 Request with Access Token gotten From Step2.
					// ------------------------------
					Objects.requireNonNull(accessToken);
					
					OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, GithubOAuth2Utils.OAUTH2_REQUEST_URI);
					log.info("\t+ Step3 - oauthRequest: {}", oauthRequest);
					
					// ------------------------------
					// Step4. Prepare a Sign-In Request with OAuth2 Access Token & Request gotten from Step2, 3
					// ------------------------------
					this.oauthService.signRequest(accessToken, oauthRequest);
					
					// ------------------------------
					// Step5. Send OAuth2 Sign-In Request & Get a Response to and from the Github OAuth2 Server.
					// ------------------------------
					Response oauthResponse = this.oauthService.execute(oauthRequest);
					log.info("\t+ Step5 - oauthResponse: {}", oauthResponse);
					
					// ------------------------------
					// Step6. Get Body from OAuth2 Response gotten from Step5.
					// ------------------------------
					Objects.requireNonNull(oauthResponse);
					
					String body = oauthResponse.getBody();
					int httpStatusCode = oauthResponse.getCode();
					Map<String, String> headers = oauthResponse.getHeaders();
					String responseMessage = oauthResponse.getMessage();

					// ------------------------------
					// Step7. Response Processing.
					// ------------------------------
					res.setCharacterEncoding("utf8");
					res.setContentType("text/html; charset=utf8");
					
					@Cleanup PrintWriter out = res.getWriter();
					
					out.println("<ol>");
					out.println("		<li>%s</li>".formatted(httpStatusCode));
					out.println("		<li>%s</li>".formatted(headers));
					out.println("		<li>%s</li>".formatted(body));
					out.println("		<li>%s</li>".formatted(responseMessage));
					out.println("</ol>");
					
					out.flush();
			} catch(Exception e) {
				throw new IOException(e);
			} // try-catch
	} // service

} // end class

