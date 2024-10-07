package org.zerock.myapp.seq_1.test.bean;

import java.io.Serial;
import java.io.Serializable;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//The control of ordering of Spring bean creation, When `@Component` annotation applied.
//@Order(Ordered.LOWEST_PRECEDENCE)								// (1) Does Not Work.
//@Priority(1)																				// (2) Does Not Work.
//@DependsOn("sample2Bean")													// (3) Does Work When (***)

//@Component("sampleBean")
public class SampleBean implements Serializable, InitializingBean, DisposableBean {
	@Serial private static final long serialVersionUID = 1L;

	
	// -- The lifecycle callbacks of a Spring Bean - 1 ------------
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
	} // postConstruct
	
	@PreDestroy
	void preDestroy() {
		log.trace("preDestroy() invoked.");
	} // preDestroy

	
	// -- The lifecycle callbacks of a Spring Bean - 2 ------------
	@Override
	public void afterPropertiesSet() throws Exception {
		log.trace("afterPropertiesSet() invoked.");
	} // afterPropertiesSet

	@Override
	public void destroy() throws Exception {
		log.trace("destroy() invoked.");
	} // destroy

	
	// -- 3 -------------
	// -- The callbacks of `@Bean(initMethod, destoryMethod)` annotation
	//	 applied to the Spring Configuration class which has `@configuration` annotation
	
	void initBean() {
		log.trace("initBean() invoked.");
	} // initBean

	void destroyBean() {
		log.trace("destroyBean() invoked.");
	} // destroyBean
	
} // end class
