package org.zerock.myapp.seq_2.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zerock.myapp.seq_1.test.bean.Sample2Bean;
import org.zerock.myapp.seq_1.test.bean.SampleBean;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Configuration("sampleConfiguration")
public class SampleConfiguration {

	
	// By default, the order of Bean creation follows the order of the declared methods to create a bean like the below. (***)

	// @Order(Ordered.HIGHEST_PRECEDENCE)										// OK, Does Not Work. (***)
	
	// Ref: BeanDefinitionValidationException: Could Not find an init method named 'initBean' on bean with name 'sampleBean'.
	@Bean(initMethod = "initBean", destroyMethod = "destroyBean")
	SampleBean sampleBean() {
		log.trace("sampleBean() invoked.");
		return new SampleBean();
	} // sampleBean
	
	
	// @Order(Ordered.LOWEST_PRECEDENCE)										// OK, Does Not Work. (***)
	
	// Ref: BeanDefinitionValidationException: Could Not find an init method named 'initBean' on bean with name 'sampleBean'.
	@Bean(initMethod = "initBean", destroyMethod = "destroyBean")
	Sample2Bean sample2Bean() {
		log.trace("sample2Bean() invoked.");
		return new Sample2Bean();
	} // sample2Bean

} // end class
