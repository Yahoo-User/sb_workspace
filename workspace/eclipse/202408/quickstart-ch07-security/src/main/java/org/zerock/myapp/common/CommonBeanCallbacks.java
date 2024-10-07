package org.zerock.myapp.common;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.NoArgsConstructor;


//@Log4j2
@NoArgsConstructor
public abstract class CommonBeanCallbacks implements InitializingBean, DisposableBean, BeanNameAware {
	@Getter protected String beanName;
	

	@Override
	public	final void setBeanName(String name) {				// BeanNameAware
//		log.trace("(1) {} -> setBeanName({}) invoked.", name, name);
		this.beanName = name;
	} // setBeanName
	
	@PostConstruct
	public void postConstruct() {												// Bean Lifecycle Callback
//		log.trace("(2) {} -> postConstruct() invoked.", this.beanName);
	} // postConstruct

	@Override
	public void afterPropertiesSet() throws Exception {		// InitializingBean
//		log.trace("(3) {} -> afterPropertiesSet() invoked.", this.beanName);
	} // afterPropertiesSet
	
	@PreDestroy
	public void preDestroy() {													// Bean Lifecycle Callback
//		log.trace("(4) {} -> preDestroy() invoked.", this.beanName);
	} // preDestroy

	@Override
	public void destroy() throws Exception {							// DisposableBean
//		log.trace("(5) {} -> destroy() invoked.", this.beanName);
	} // destroy

} // end class
