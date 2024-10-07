package org.zerock.myapp.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2

//@ToString
@NoArgsConstructor

@SessionScope

@Component("sessionScopeBean")
public class SessionScopeBean 
	implements InitializingBean, DisposableBean, BeanNameAware {
	
	@Setter @Getter
	private Object container;
	
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
		log.info("\t+ this.container: {}", this.container);
	} // postConstruct
		
	@Override
	public void setBeanName(String name) {
		log.trace("setBeanName({}) invoked.", name);
	} // setBeanName

	@Override
	public void afterPropertiesSet() throws Exception {
		log.trace("afterPropertiesSet() invoked.");
	} // afterPropertiesSet

	@Override
	public void destroy() throws Exception {
		log.trace("destroy() invoked.");
	} // destroy

} // end class
