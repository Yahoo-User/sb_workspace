package org.zerock.myapp.servlet.google;


public interface GoogleOAuth2Utils {
	
		// This name is only used to identify the client in the console and will not be shown to end users
		public static final String CLIENT_NAME = "Yoseph";
		
		// The client secrets for web application provided by Google Cloud Console.
		public static final String CLIENT_ID 			= "543020721770-lvf1u0ffn2u9ioctt346oju2r10rg7j2.apps.googleusercontent.com";
		public static final String CLIENT_SECRET 	= "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";
		
		public static final String AUTHORIZATION_URI 	= "https://accounts.google.com/o/oauth2/auth";
		public static final String REDIRECT_URI	= "https://localhost/login/oauth2/code/google";
		public static final String TOKEN_URI	= "https://oauth2.googleapis.com/token";
		public static final String USERINFO_URI	= "https://www.googleapis.com/oauth2/v3/userinfo";
		
		// *Not used constants to connect with Google Login.
		public static final String PROJECT_ID = "myproject-427322";
		public static final String AUTH_PROVIDER_X509_CERT_URL = "https://www.googleapis.com/oauth2/v1/certs";
		
		// Etc.
		public static final String SCOPE = "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email";
		public static final String RESPONSE_TYPE = "code";
				
		

} // end interface


