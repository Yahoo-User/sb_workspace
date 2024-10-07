package org.zerock.myapp.jdbc;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;


@Log4j2

@EnableConfigurationProperties( JDBCProperties.class )

@AutoConfiguration("JDBCAutoConfiguration")
@ConditionalOnClass( { JDBCProperties.class, JDBCConnectionDetails.class } )
@ConditionalOnProperty(prefix = "jdbc.connection", name = { "driver", "url", "user", "pass" })
public class JDBCAutoConfiguration implements InitializingBean, DisposableBean, BeanNameAware  {
	@Getter private String myBeanName;
	private final JDBCProperties properties;


	public JDBCAutoConfiguration(JDBCProperties properties) {
		log.trace(">>> Bean Lifecyle Step1 - Constructor JDBCAutoConfiguration({}) invoked.", properties);
		this.properties = properties;
	} // Default Constructor

	@Override
	public void setBeanName(String myBeanName) {
		log.trace(">>> Bean Lifecyle Step2 - BeanNameAware.setBeanName({}) invoked.", myBeanName);
		this.myBeanName = myBeanName;
//		String originalBeanName = BeanFactoryUtils.originalBeanName(myBeanName);
//		log.info("\t+ originalBeanName: {}", originalBeanName);
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
	
	@Bean("jdbcConnectionDetails")
	JDBCConnectionDetails jdbcConnectionDetails() {
		log.trace("jdbcConnectionDetails() invoked.");		
		return new JDBCConnectionDetails(this.properties);
	} // jdbcConnectionDetails

} // end class

