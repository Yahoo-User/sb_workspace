package org.zerock.myapp.security.controller.auth;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.myapp.security.domain.Credential;
import org.zerock.myapp.security.domain.UserDTO;
import org.zerock.myapp.security.domain.UserVO;
import org.zerock.myapp.security.service.AuthService;
import org.zerock.myapp.security.service.UserService;
import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(SharedAttributes.SIGNIN_URI)
public class SignInControllerServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace(">>> service(req, res) invoked.");
		
		HttpServletMapping mapping = req.getHttpServletMapping();
		log.info("\t+ matchValue: {}, pattern: {}, servletName: {}, mappingMatch: {}", 
				mapping.getMatchValue(), mapping.getPattern(), mapping.getServletName(), mapping.getMappingMatch());
		
		try {
			UserService userService = new UserService();
			AuthService authService = new AuthService();
			
			// Step1. Destroy no longer used current session if current session exists & 
			//			   Create a new session for the current client web browser to newly sign in.
			HttpSession newSession = authService.createSession(req);
			
			// Step2. Get all request parameters to sign in.
			final String username = req.getParameter("username");
			final String password = req.getParameter("password");
			
			// Step3. Create a UserDTO.
			UserDTO dto = new UserDTO();
			
			dto.setUsername(username);
			dto.setPassword(password);
			
			log.info("\t+ dto: {}", dto);
			
			// Step4. Create a credential for the current signed-in user
			//				and Set this credential to the session scope for current web browser.
			UserVO vo = userService.findByUsername(dto);
			
			Credential credential = authService.createCredential(vo);
			newSession.setAttribute(SharedAttributes.CREDENTIAL, credential);

			// Step5. Re-directing to the next UI (Login UI or Main UI)
			if(vo == null) {	// If a user Not found.
				// (1) Re-direct to the "SharedAttributes.LOGIN_URI".
				authService.redirectTo(res, SharedAttributes.LOGIN_URI);
			} else {				// If a user found.
				// (2) Re-direct to the "SharedAttributes.MEMBER_URI".
				authService.redirectTo(res, SharedAttributes.MEMBER_URI);
			} // if-else
			
			
			log.info("Done.");
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class

