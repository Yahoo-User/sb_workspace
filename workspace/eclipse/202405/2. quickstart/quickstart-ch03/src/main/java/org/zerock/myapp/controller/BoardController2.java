package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller
public class BoardController2 {
	
	@GetMapping("/returnViewName")
	String returnViewName() {
		log.trace("returnViewName() invoked.");
		return "viewName";
	} // returnViewName
	
	@PostMapping("/returnModelAndView")
	String returnModelAndView(String key1, String key2, Model model) {
		log.trace("returnModelAndView() invoked.");
		
		model.addAttribute("key1" , key1);
		model.addAttribute("key2",  key2);
		
		return "viewName";
	} // returnModelAndView
	
	@GetMapping("/redirectWithParameters")
	String redirectWithParameters(String key1, String key2, RedirectAttributes rttrs) {
		log.trace("redirectWithParameters() invoked.");
		
		rttrs.addAttribute("key1", key1);
		rttrs.addAttribute("key2", key2);
		
		return "redirect:/returnModelAndView";
	} // redirectWithParameters
	
	
	@GetMapping("/returnCookies")
	String returnCookies(HttpServletResponse res) {
		log.trace("returnCookies({}) invoked.", res);
		
		Cookie cookie1 = new Cookie("cookieName1", "cookieValue1");
		cookie1.setMaxAge(1000);		// an integer specifying the maximum age of the cookie in seconds
		
		Cookie cookie2 = new Cookie("cookieName2", "cookieValue2");
		cookie2.setMaxAge(0);			// if negative, means the cookie is not stored; if zero, deletes the cookie
		
		res.addCookie(cookie1);
		res.addCookie(cookie2);

		return "/returnViewName";				// OK: Forwarded URL
//		return "forward:/returnViewName";		// OK: Forwarded URL
		
	} // returnCookies
	
	
	/*
	 * ------------------------
	 * @PostConstruct
	 * ------------------------
	 * the `PostConstruct` annotation is applied MUST fulfill all of the following criteria: 
	 * 		•The method MUST NOT have any parameters except in the case of interceptors
	 * 		  in which case it takes an `InvocationContext` object as defined by the Jakarta Interceptors specification.
	 * 		•The method defined on an interceptor class or superclass of an interceptor class must have one of the following signatures: 
	 * 			void <METHOD>(InvocationContext) 
	 * 			Object <METHOD>(InvocationContext) throws Exception 
	 * 
	 * Note: A `PostConstruct` interceptor method MUST NOT throw application exceptions, 
	 * 			 but it may be declared to throw checked exceptions including the `java.lang.Exception` 
	 * 			 if the same interceptor method interposes on business or timeout methods in addition to lifecycle events.
	 * 			If a `PostConstruct` interceptor method returns a value, it is ignored by the container. 
	 * 
	 * 		•The method defined on a non-interceptor class must have the following signature: 
	 * 			void <METHOD>()
	 * 		•The method on which the `PostConstruct` annotation is applied may be public, protected, package private or private.
	 * 		•The method MUST NOT be static except for the application client.
	 * 		•The method should NOT be final.
	 * 		•If the method throws an unchecked exception the class MUST NOT be put into service
	 * 		 except in the case where the exception is handled by an interceptor.
	 */
	@PostConstruct
	public void postConstruct() {
		log.trace("postConstruct() invoked.");
	} // postConstruct
	
	@PreDestroy
	public void preDestroy() {
		log.trace("preDestroy() invoked.");
	} // preDestroy

} // end class
