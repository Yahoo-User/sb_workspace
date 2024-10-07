package org.zerock.myapp.jdbc;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Data

@ConfigurationProperties(prefix = "jdbc.connection")
public class JDBCProperties implements InitializingBean, DisposableBean, BeanNameAware {
	private String myBeanName;
	
	// All field types should be the wrapper types against each primitive type.
	private String driver;
	private String url;
	private String user;
	private String pass;
	
	
	@ConstructorBinding
	public JDBCProperties(String driver, String url, String user, String pass) {
		log.trace(">>> Bean Lifecyle Step1 - Constructor JDBCProperties({}, {}, {}, {}) invoked.", driver, url, user, pass);
		
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pass = pass;
	} // Constructor

	@Override
	public void setBeanName(String myBeanName) {
		log.trace(">>> Bean Lifecyle Step2 - BeanNameAware.setBeanName({}) invoked.", myBeanName);
		this.myBeanName = myBeanName;
	} // setBeanName
	
	@PostConstruct
	void postConstruct() {
		log.trace(">>> Bean Lifecyle Step3 - @PostConstruct invoked.");
	} // postConstruct

	@Override
	public void afterPropertiesSet() {
		log.trace(">>> Bean Lifecyle Step4 - InitializingBean.afterPropertiesSet() invoked.");
	} // afterPropertiesSet

	@PreDestroy
	void preDestroy() {
		log.trace(">>> Bean Lifecyle Step5 - @PreDestroy invoked.");
	} // preDestroy
	
	@Override
	public void destroy() {
		log.trace(">>> Bean Lifecyle Step6 - DisposableBean.destroy() invoked.");
	} // destroy
	
} // end class
