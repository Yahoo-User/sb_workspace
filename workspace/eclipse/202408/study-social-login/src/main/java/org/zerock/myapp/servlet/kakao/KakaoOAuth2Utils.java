package org.zerock.myapp.servlet.kakao;



public interface KakaoOAuth2Utils {
	
	// (1) Application Name Registered To The Kakao Developers.
	public static final String APP_NAME = "LoginTest";

	// (2) Client ID Required From The Kakao OAuth2 Authorization Server.
	// REST API를 호출할 때 사용합니다.
	public static final String REST_API_KEY = "c30eddcf8b175d493f04783fcdf3ee26";
	
	// (3) This Equals To (2)
	public static final String CLIENT_ID = REST_API_KEY;

	// (4) Callback URL from Kakao Authorization Server.
	public static final String REDIRECT_URI = "https://localhost/RedirectionUriFromKakaoLogin";

	// (5) Kakao Authorization Server URL To Be Used To Send OAuth2 Request.
	public static final String REQUEST_URI = "https://kauth.kakao.com/oauth/authorize";
	
	// (6) Kakao Token URL To Get Access Token, Refresh Token.
	public static final String TOKEN_URI = "https://kauth.kakao.com/oauth/token";
	
	// (7) Kakao Profile URL To Get User Information With Access Token.
	public static final String PROFILE_URI = "https://kapi.kakao.com/v2/user/me";
	
	// (8) The Reponse Type Sent From The Kakao Authorization Server.
	public static final String RESPONSE_TYPE = "code";
	
	// (9) GRANT_TYPE
	public static final String GRANT_TYPE = "authorization_code";
	
	
} // end interface
