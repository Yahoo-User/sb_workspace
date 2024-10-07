package org.zerock.myapp.servlet.naver;

import java.util.UUID;


public interface NaverOAuth2Utils {
	
	public static final String APP_NAME = "LoginTest";
	public static final String CLIENT_ID = "o8H_BWzykey1aTt1j7bS";
	public static final String CLIENT_SECRET = "Ja8HfUTjzs";
	public static final String REDIRECT_URI = "https://localhost/RedirectionUriFromNaverLogin";
	
	public static final String OAUTH2_REQUEST_URI = "https://nid.naver.com/oauth2.0/authorize";
	public static final String TOKEN_REQUEST_URI = "https://nid.naver.com/oauth2.0/token";
	public static final String PROFILE_REQUEST_URI = "https://openapi.naver.com/v1/nid/me";
	
	public static final String RESPONSE_TYPE = "code";
	public static final String GRANT_TYPE = "authorization_code";
	public static final String STATE = UUID.randomUUID().toString();
	

} // end interface
