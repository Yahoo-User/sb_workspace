package org.zerock.myapp.security.servlet.controller;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.security.domain.Model;
import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(SharedAttributes.USER_URI)
public class UserControllerServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;


	// The controller to do all request control for the request.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace(">>> service(req, res) invoked.");
		
		HttpServletMapping mapping = req.getHttpServletMapping();
		log.info("\t+ matchValue: {}, pattern: {}, servletName: {}, mappingMatch: {}", 
				mapping.getMatchValue(), mapping.getPattern(), mapping.getServletName(), mapping.getMappingMatch());
		
		try {
			// Step1. Add required data to the Model.
			Model<String> model = new Model<>(req);
			
			model.add("BIZDATA1", "VALUE1");
			model.add("BIZDATA2", "VALUE2");
			model.add("BIZDATA3", "VALUE3");
			
			// Step2. Set original request URI & view name & required role to the request scope.
			String requestURI = req.getRequestURI();
			
			// View Resolving: resolvedViewPath = VIEW_PREFIX + viewName + VIEW_SUFFIX
			String viewName = "user";

			// ROLE_USER, ROLE_ADMIN, ROLE_AUTHENTICATED, ROLE_ANONYMOUS
			String requiredRole = SharedAttributes.ROLE_USER;
			
			req.setAttribute(SharedAttributes.REQUEST_URI, requestURI);
			req.setAttribute(SharedAttributes.VIEW_NAME, viewName);
			req.setAttribute(SharedAttributes.REQUIRED_ROLE, requiredRole);
			
			// Step3. Determine Whether Doing Authentication.
			boolean doAuthenticate = true;
			boolean doAuthorize = true;
			
			req.setAttribute(SharedAttributes.DO_AUTHENTICATE, doAuthenticate);
			req.setAttribute(SharedAttributes.DO_AUTHORIZE, doAuthorize);
			
			// Step4. Do Authentication or View Resolving with SharedAttributes.AUTHENTICATE.
			RequestDispatcher dispatcher;
			
			if(doAuthenticate) {
				// (1) forward to the SharedAttributes.AUTHENTICATE_URI.
				dispatcher = req.getRequestDispatcher(SharedAttributes.AUTHENTICATE_URI);
			} else {
				// (2) forward to the SharedAttributes.VIEW_RESOLVER_URI.
				dispatcher = req.getRequestDispatcher(SharedAttributes.VIEW_RESOLVER_URI);
			} // if-else
			
			dispatcher.forward(req, res);
			
			
			log.info("Done.");
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class

