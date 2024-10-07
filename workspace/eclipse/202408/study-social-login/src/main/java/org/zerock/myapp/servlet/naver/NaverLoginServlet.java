package org.zerock.myapp.servlet.naver;

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

@WebServlet("/NaverLogin")
public class NaverLoginServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			log.trace("service(req, res) invoked.");
			
			String requestNaverLoginURL = 
					"%s?client_id=%s&redirect_uri=%s&response_type=%s&state=%s".
					formatted(
							NaverOAuth2Utils.OAUTH2_REQUEST_URI, 
							NaverOAuth2Utils.CLIENT_ID, 
							NaverOAuth2Utils.REDIRECT_URI, 
							NaverOAuth2Utils.RESPONSE_TYPE, 
							NaverOAuth2Utils.STATE
					);
			
			log.info("\t+ requestNaverLoginURL: {}", requestNaverLoginURL);
			
			res.sendRedirect(requestNaverLoginURL);
			
			log.info("Done.");
	} // service

} // end class

