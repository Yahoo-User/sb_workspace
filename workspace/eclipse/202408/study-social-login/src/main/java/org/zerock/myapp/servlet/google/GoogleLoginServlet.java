package org.zerock.myapp.servlet.google;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@Slf4j

@NoArgsConstructor

@WebServlet("/GoogleLogin")
public class GoogleLoginServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			log.trace("service(req, res) invoked.");
			
			String requestGoogleLoginURL = 
					"%s?client_id=%s&redirect_uri=%s&response_type=%s&scope=%s".
					formatted(
							GoogleOAuth2Utils.AUTHORIZATION_URI, 
							GoogleOAuth2Utils.CLIENT_ID, 
							GoogleOAuth2Utils.REDIRECT_URI, 
							GoogleOAuth2Utils.RESPONSE_TYPE, 
							GoogleOAuth2Utils.SCOPE
					);
			
			log.info("\t+ requestGoogleLoginURL: {}", requestGoogleLoginURL);
			
			res.sendRedirect(requestGoogleLoginURL);
	} // service

} // end class

