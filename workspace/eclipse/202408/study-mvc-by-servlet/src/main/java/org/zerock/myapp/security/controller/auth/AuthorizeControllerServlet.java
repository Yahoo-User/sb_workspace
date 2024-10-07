package org.zerock.myapp.security.controller.auth;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.myapp.security.service.AuthService;
import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(SharedAttributes.AUTHORIZE_URI)
public class AuthorizeControllerServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	/**
	 * Check whether current web browser have authority to access the request URI or Not.
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace(">>> service(req, res) invoked.");
		
		HttpServletMapping mapping = req.getHttpServletMapping();
		log.info("\t+ matchValue: {}, pattern: {}, servletName: {}, mappingMatch: {}", 
				mapping.getMatchValue(), mapping.getPattern(), mapping.getServletName(), mapping.getMappingMatch());
		
		try {
			// Step1. Get current HTTP session if present.
			HttpSession session = req.getSession(false);
			
			// Step2. Get required role from request scope.
			String requiredRole = (String) req.getAttribute(SharedAttributes.REQUIRED_ROLE);
			log.info("\t+ requiredRole: {}", requiredRole);

			// Step3. Authorize current request.
			AuthService authService = new AuthService();
			
			boolean isAuthorized = authService.isAuthorized(session, requiredRole);
			log.info("\t+ isAuthorized: {}", isAuthorized);
			
			// Step4. If Not authorized, Re-direct to the `SharedAttributes.FORBIDDEN_URI`".
			//			    If authenticated & authorized, forward to the `SharedAttributes.VIEW_RESOLVER_URI`.
			if(!isAuthorized) {		// if Not authorized.
				res.sendRedirect(SharedAttributes.FORBIDDEN_URI);
				log.info("\t+ Re-direction to the {} Done.", SharedAttributes.FORBIDDEN_URI);
			} else {							// if authorized.
				RequestDispatcher dispatcher = req.getRequestDispatcher(SharedAttributes.VIEW_RESOLVER_URI);
				dispatcher.forward(req, res);
			} // if-else
			
			
			log.info("Done.");
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class

