package org.zerock.myapp.seq_13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.seq_12.scope.ApplicationScopeBean;
import org.zerock.myapp.seq_12.scope.RequestScopeBean;
import org.zerock.myapp.seq_12.scope.SessionScopeBean;
import org.zerock.myapp.seq_12.scope.SingletonScopeBean;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/scope")
@Controller("scopeController")
public class ScopeController {
	/**
	 * Important:
	 * 		(1) All Scoped Beans Should Be Injected To The Declared Field.
	 * 		(2) All Scoped Beans Should *Not Be Injected To The *Method *Parameters.		(***)
	 */
	@Autowired private RequestScopeBean requestScopeBean;							// Correct Use
	@Autowired private SessionScopeBean sessionScopeBean;							// Correct Use - 1
	@Autowired private ApplicationScopeBean applicationScopeBean;				// Correct Use
	@Autowired private SingletonScopeBean singletonScopeBean;						// Correct Use - 1
	
	@Autowired private ScopeService scopeService;
	
	
	@GetMapping("/request")
	void requestScope(Model model) {
		log.trace("requestScope({}) invoked.", model);
		
		model.addAttribute("requestScopeBean", this.requestScopeBean);
		model.addAttribute("message", "Correct Use");
	} // requestScope
	
	@GetMapping("/request2")
	String requestScope(
			@Autowired RequestScopeBean requestScopeBean, 								// Incorrect Use
			Model model) {
		log.trace("requestScope({}, {}) invoked.", requestScopeBean, model);
		
		model.addAttribute("requestScopeBean", requestScopeBean);
		model.addAttribute("message", "Incorrect Use");
		
		return "scope/request";
	} // requestScope
	
	@GetMapping("/session")
	void sessionScope1(Model model) {
		log.trace("sessionScope1({}) invoked.", model);
		
		model.addAttribute("sessionScopeBean", this.sessionScopeBean);			// Correct Use - 1
		model.addAttribute("message", "Correct Use");
	} // sessionScope1
	
	@GetMapping("/session2")
	String sessionScope2(Model model) {
		log.trace("sessionScope({}) invoked.", model);
		
		SessionScopeBean sessionScopeBean = this.scopeService.doit();				// Correct Use - 2
		
		model.addAttribute("sessionScopeBean", sessionScopeBean);
		model.addAttribute("message", "Correct Use");
		
		return "scope/session";
	} // sessionScope
	
	@GetMapping("/session3")
	String sessionScope3(
			@Autowired SessionScopeBean sessionScopeBean, 								// Incorrect Use
			Model model) {
		log.trace("sessionScope3({}, {}) invoked.", sessionScopeBean, model);
		
		model.addAttribute("sessionScopeBean", sessionScopeBean);
		model.addAttribute("message", "Incorrect Use");
		
		return "scope/session";
	} // sessionScope3
	
	@GetMapping("/application")
	void applicationScopeBean(Model model) {
		log.trace("applicationScopeBean({}) invoked.", model);
		
		model.addAttribute("applicationScopeBean", this.applicationScopeBean);	// Correct Use
		model.addAttribute("message", "Correct Use");
	} // applicationScopeBean
	
	@GetMapping("/application2")
	String applicationScopeBean(
			@Autowired ApplicationScopeBean applicationScopeBean, 						// Incorrect Use
			Model model) {
		log.trace("applicationScopeBean({}) invoked.", model);
		
		model.addAttribute("applicationScopeBean", applicationScopeBean);
		model.addAttribute("message", "Incorrect Use");
		
		return "scope/application";
	} // applicationScopeBean
	
	@GetMapping("/singleton")
	void singletonScopeBean(Model model) {
		log.trace("singletonScopeBean({}) invoked.", model);
		
		model.addAttribute("singletonScopeBean", this.singletonScopeBean);		// Correct Use - 1
		model.addAttribute("message", "Correct Use");
	} // singletonScopeBean
	
	@GetMapping("/singleton2")
	String singletonScopeBean(
			@Autowired SingletonScopeBean singletonScopeBean, 							// Incorrect Use
			Model model) {
		log.trace("singletonScopeBean({}) invoked.", model);
		
		model.addAttribute("singletonScopeBean", singletonScopeBean);
		model.addAttribute("message", "Incorrect Use");
		
		return "scope/singleton";
	} // singletonScopeBean

} // end class

