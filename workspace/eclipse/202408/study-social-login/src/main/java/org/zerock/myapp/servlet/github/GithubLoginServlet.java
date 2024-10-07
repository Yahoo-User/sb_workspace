package org.zerock.myapp.servlet.github;

import java.io.IOException;
import java.io.Serial;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.scribejava.apis.GitHubApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@Slf4j

@NoArgsConstructor

@WebServlet("/GithubLogin")
public class GithubLoginServlet extends HttpServlet {
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
					// --------------
					String authorizationUrl = this.oauthService.getAuthorizationUrl();
					log.info("\t+ authorizationUrl: {}", authorizationUrl);
					
					Objects.requireNonNull(authorizationUrl);
					
					res.sendRedirect(authorizationUrl);
			} catch(Exception e) {
				throw new IOException(e);
			} // try-catch
	} // service

} // end class

