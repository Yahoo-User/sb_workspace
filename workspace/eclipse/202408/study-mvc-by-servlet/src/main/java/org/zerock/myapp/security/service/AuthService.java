package org.zerock.myapp.security.service;

import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.myapp.security.domain.Credential;
import org.zerock.myapp.security.domain.UserVO;
import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class AuthService {

	// Important: 
	//		The `@PostConstruct` & `@PreDestroy` annotations do work *Only in the java *Servlet class.	

	public Credential createCredential(UserVO vo) throws Exception {
		log.trace("createCredential({}) invoked.", vo);
		
		Credential credential = null;
		
		if(vo != null) {
			credential = new Credential(vo.getUsername(), vo.getRole());
		} // if
		
		log.info("\t+ credential: {}", credential);
		
		return credential;
	} // createCredential
	
	public Credential getCredential(HttpSession session) throws Exception {
		log.trace("getCredential(session) invoked.");

		// -----------
		Objects.<HttpSession>requireNonNullElse(session, null);

		// -----------
		Object obj = session.getAttribute(SharedAttributes.CREDENTIAL);
		log.info("\t+ obj: {}", obj);

		// -----------
		if(obj instanceof Credential credential) return credential;
		else 	return null;
	} // getCredential
	
	public boolean isAuthenticated(HttpSession session) throws Exception {
		log.trace("isAuthenticated(session) invoked.");
		
		if(session == null) return false;
		Credential credential = this.getCredential(session);
		
		return ( credential != null );
	} // isAuthenticated
	
	public boolean isAuthorized(HttpSession session, String requiredRole) throws Exception {
		log.trace("isAuthorized(session, {}) invoked.", requiredRole);
		
		// -----------
		if(session == null) return false;

		// -----------
		Credential credential = this.getCredential(session);
		if(credential == null) return false;
		
		log.info("\t+ requiredRole({}), credential({})", requiredRole, credential);

		// -----------
		if(requiredRole.equals(credential.getRole())) return true;
		else return false;
	} // isAuthorized
	
	public void destroySession(HttpSession session) throws Exception {
		log.trace("destroySession(session) invoked.");
		
		if(session != null) {
			String sessionId = session.getId();
			Date creationTime = new Date(session.getCreationTime());
			
			session.invalidate();
			
			log.info("\t+ Current Session(%s) Created At (%s) Destroyed.".formatted(sessionId, creationTime));
		} // if
	} // destroySession
	
	public HttpSession createSession(HttpServletRequest req) throws Exception {
		log.trace("createSession(req) invoked.");
		
		// ----------
		Objects.requireNonNull(req);

		// ----------
		HttpSession oldSession = req.getSession(false);
		this.destroySession(oldSession);

		// ----------
		HttpSession newSession  = req.getSession(true);
		String sessionId = newSession.getId();
		Date creationTime = new Date(newSession.getCreationTime());
		
		log.info("\t+ New Session(%s) Is Created At (%s).".formatted(sessionId, creationTime));
		
		return newSession;
	} // createSession
	
	public void redirectTo(HttpServletResponse res, String redirectURI) throws Exception {
		log.trace("redirectTo(res, {}) invoked.", redirectURI);

		// ----------
		Objects.requireNonNull(res);
		Objects.requireNonNull(redirectURI);

		// ----------
		res.sendRedirect(redirectURI);
		log.info("\t+ Re-Directed to the {}", redirectURI);
	} // redirectTo
	

} // end class
