package org.zerock.myapp.config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;


@Log4j2

//@Order(Ordered.LOWEST_PRECEDENCE - 100)
@Configuration
@ConditionalOnProperty(name = "test-http-protocol", havingValue = "HTTPs")
class RestTemplateWithSSLConfig {
	@Value("${trust-store}")
	private Resource trustStore;
	
	@Value("${trust-store-password}")
	private String trustStorePassword;

	
	RestTemplateWithSSLConfig() {
		log.trace("Default constructor invoked.");
		
		log.info("+ this.trustStore: {}", this.trustStore);									// XX : @Value("${trust-store}")
		log.info("+ this.trustStorePassword: {}", this.trustStorePassword);		// XX : @Value("${trust-store-password}")
	} // Default Constructor

    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    RestTemplate restTemplateWithSSL()
            throws KeyManagementException, 
            			NoSuchAlgorithmException, 
            			KeyStoreException, 
            			CertificateException, 
            			IOException, 
            			NoSuchProviderException {
		log.trace("restTemplateWithSSL() invoked.");
		
		log.info("+ this.trustStore: {}", this.trustStore);									// OK : @Value("${trust-store}")
		log.info("+ this.trustStorePassword: {}", this.trustStorePassword);		// OK : @Value("${trust-store-password}")
		log.info("-----------------");
		
		// ------------- Using org.apache.httpcomponents.client5:httpclient5 library ------------- //
		
		// -------
		// Step1. 	Create a `SSLContext`
		//				with `SSLContextBuilder` and the url & password of the trust store.
		// -------
//		SSLContext sslCtx = 						// 1st. method
//				new SSLContextBuilder()
//					.loadTrustMaterial(this.trustStore.getURL(), this.trustStorePassword.toCharArray())
//					.build();

		// -------
		SSLContext sslCtx = 						// 2nd. method
				new SSLContextBuilder()
					.loadTrustMaterial(this.trustStore.getURL(), this.trustStorePassword.toCharArray(), (chain, authType) -> {
						log.trace("TrustStrategy.isTrusted({}, {}) invoked.", Arrays.toString(chain), authType);
						return true;
					}).build();
		
		// -------
		log.info("+ Step1. sslCtx: {}", sslCtx);

		// -------
		// Step2. 	Create a `SSLConnectionSocketFactory` with `SSLContext`.
		// -------
//		SSLConnectionSocketFactory sslConnSockFactory = new SSLConnectionSocketFactory(sslCtx);			// 1st. method
		
		// ----
		
		SSLConnectionSocketFactory sslConnSockFactory = 																		// 2nd. method
				new SSLConnectionSocketFactory(sslCtx, (hostname, session) -> {	// HostnameVerifier
					log.trace("HostnameVerifier.verify({}, {}) invoked.", hostname, session);
					return true;
				});	// new SSLConnectionSocketFactory

		// ----
		log.info("+ Step2. sslConnSockFactory: {}", sslConnSockFactory);
		
		// -------
		// Step3. 	Create a `HttpClientConnectionManager`
		//				with `PoolingHttpClientConnectionManagerBuilder` & `SSLConnectionSocketFactory`
		// -------
		HttpClientConnectionManager connMgr = 
				PoolingHttpClientConnectionManagerBuilder.create()
					.setSSLSocketFactory(sslConnSockFactory)
					.build();

		log.info("+ Step3. connMgr: {}", connMgr);
		
		// -------
		// Step4. Create a `CloseableHttpClient` with `HttpClients` Helper and `HttpClientConnectionManager`
		// -------
		CloseableHttpClient httpClient = 
				HttpClients.custom()
					.setConnectionManager(connMgr)
					.build();

		log.info("+ Step4. httpClient: {}", httpClient);
		
		// -------
		// Step5. Crearte a `RequestFactory` with `HttpComponentsClientHttpRequestFactory` and HttpClient.
		// -------
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		log.info("+ Step5. requestFactory: {}", requestFactory);
		
		// -------
		// Step6. Create a `RestTemplate` with `RequestFactory`.
		// -------
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		log.info("+ Step6. restTemplate: {}", restTemplate);
		
		
		return restTemplate;
	} // restTemplateWithSSL
	
} // end class
