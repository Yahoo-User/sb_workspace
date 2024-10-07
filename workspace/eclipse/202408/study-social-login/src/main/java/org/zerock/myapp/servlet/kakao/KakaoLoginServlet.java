package org.zerock.myapp.servlet.kakao;

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

@WebServlet("/KakaoLogin")
public class KakaoLoginServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		String kakaoOAuth2URL = 
				KakaoOAuth2Utils.REQUEST_URI + 
				"?response_type=" + KakaoOAuth2Utils.RESPONSE_TYPE + 
				"&client_id=" + KakaoOAuth2Utils.CLIENT_ID + 
				"&redirect_uri=" + KakaoOAuth2Utils.REDIRECT_URI;
		
		log.info("\t+ kakaoOAuth2URL: {}", kakaoOAuth2URL);
		
		res.sendRedirect(kakaoOAuth2URL);
	} // service

} // end class

