package org.zerock.myapp.servlet.github;


public interface GithubOAuth2Utils {
	
		// This name is only used to identify the client in the console and will not be shown to end users
		public static final String APP_NAME = "LoginTest";

		// The client secrets for web application provided by Github.
		public static final String CLIENT_ID = "Ov23li2CJUrLlBCxXCps";
		public static final String CLIENT_SECRET = "d9701bca1b59e6964f09b9f1dc45abd9d7d659ca";
		public static final String REDIRECT_URI = "https://localhost/login/oauth2/code/github";
		public static final String OAUTH2_REQUEST_URI = "https://api.github.com/user";

		// Etc.
		public static final String DEFAULT_SCOPE = "user";
		
} // end interface

