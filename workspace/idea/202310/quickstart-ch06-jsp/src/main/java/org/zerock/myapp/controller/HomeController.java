package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ModelAttribute;


@Log4j2

// Class contains `required fields`, you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Controller
public class HomeController {


	/** ****************************************************
	 * @ModelAttribute('key')
	 * ****************************************************
	 * Annotation that binds a method parameter or method return value to a named model attribute, exposed to a web view.
	 * Supported for controller classes with @RequestMapping methods.
	 *
	 * WARNING: Data binding can lead to security issues by exposing parts of the object graph that are not meant to be accessed or modified by external clients.
	 * Therefore the design and use of data binding should be considered carefully with regard to security.
	 * For more details, please refer to the dedicated sections on data binding for Spring Web MVC  and Spring WebFlux in the reference manual.
	 *
	 * `@ModelAttribute` can be used to expose command objects to a web view, using specific attribute names,
	 * by annotating corresponding parameters of an `@RequestMapping` method.
	 *
	 * `@ModelAttribute` can also be used to expose reference data to a web view by annotating accessor methods in a controller class with `@RequestMapping` methods.
	 * Such accessor methods are allowed to have any arguments that `@RequestMapping` methods support, returning the model attribute value to expose.
	 *
	 * Note however that reference data and all other model content are not available to web views when request processing results in an Exception
	 * since the exception could be raised at any time making the content of the model unreliable.
	 *
	 * For this reason `@ExceptionHandler` methods do not provide access to a Model argument.
	 */

	@ModelAttribute("key")
	String createTempModel() {
		log.trace("createTempModel() invoked.");

		return "value";
	} // createTempModel
	
	
	@GetMapping("/home")
	void home(@ModelAttribute("key") String value) {
		log.trace("home({}) invoked.", value);
		
	} // home
	
	
	@GetMapping("/hello")
	void hello(Model model) {
		log.trace("hello({}) invoked.", model);

		model.addAttribute("greeting", "Hello, Thymeleaf !!!");
	} // hello

} // end class
