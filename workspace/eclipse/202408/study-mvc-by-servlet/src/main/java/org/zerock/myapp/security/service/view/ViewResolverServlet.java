package org.zerock.myapp.security.service.view;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(SharedAttributes.VIEW_RESOLVER_URI)
public class ViewResolverServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace(">>> service(req, res) invoked.");
		
		HttpServletMapping mapping = req.getHttpServletMapping();
		log.info("\t+ matchValue: {}, pattern: {}, servletName: {}, mappingMatch: {}", 
				mapping.getMatchValue(), mapping.getPattern(), mapping.getServletName(), mapping.getMappingMatch());
		
		try {
			// Step1. Resolving Complete View Path with View Prefix & Suffix & viewName
			String viewName = (String) req.getAttribute(SharedAttributes.VIEW_NAME);
			String resolvedViewPath = SharedAttributes.VIEW_PREFIX + viewName + SharedAttributes.VIEW_SUFFIX;
			
			log.info("\t+ viewName: {}, resolvedViewPath: {}", viewName, resolvedViewPath);
			
			// Step2. Set resolved view path to the request scope.
			req.setAttribute(SharedAttributes.RESOLVED_VIEW_PATH, resolvedViewPath);
			
			// Step2. Request Forwading to the SharedAttributes.VIEW_URI with resolved view path.
			RequestDispatcher dispatcher = req.getRequestDispatcher(SharedAttributes.VIEW_URI);
			dispatcher.forward(req, res);
			
			log.info("\t+ Done.");
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class

