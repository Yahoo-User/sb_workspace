package org.zerock.myapp.jdbc;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2
@ToString
public class JDBCConnectionDetails implements InitializingBean, DisposableBean, BeanNameAware  {
	@Getter private String myBeanName;
	private final JDBCProperties properties;
	

	public JDBCConnectionDetails(JDBCProperties properties) {
		log.trace(">>> Bean Lifecyle Step1 - Constructor JDBCConnectionDetails({}) invoked.", properties);
		this.properties = properties;
	} // Default constructor

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
	
	public String getDriver() {
		log.trace("getDriver() invoked.");
		return this.properties.getDriver();
	} // getDriver
	
	public String getUrl() {
		log.trace("getUrl() invoked.");
		return this.properties.getUrl();
	} // getUrl
	
	public String getUser() {
		log.trace("getUser() invoked.");
		return this.properties.getUser();
	} // getUser
	
	public String getPass() {
		log.trace("getPass() invoked.");
		return this.properties.getPass();
	} // getPass
	
} // end class

