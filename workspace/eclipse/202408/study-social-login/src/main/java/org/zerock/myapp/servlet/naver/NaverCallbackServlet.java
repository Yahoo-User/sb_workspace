package org.zerock.myapp.servlet.naver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serial;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@Slf4j

@NoArgsConstructor

@WebServlet("/RedirectionUriFromNaverLogin")
public class NaverCallbackServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			log.trace("service(req, res) invoked.");
			
			try {
					Map<String, String[]> paramMap = req.getParameterMap();
					paramMap.forEach((k, v) -> log.info("\t+ param: {}({})", k, v));
					
					// -----------------------------------
					// Step1. Get "code", "state" Request Parameters.
					// -----------------------------------
					
					String code = req.getParameter("code");
					String state = req.getParameter("state");
					
					log.info("\t+ Step1 - code: {}, state: {}", code, state);
					
					if(!NaverOAuth2Utils.STATE.equals(state)) {	// CRSF attacked.
						log.info("\t+ Step1 - CSRF attacked: originalState({}) != receivedState({})", 
								NaverOAuth2Utils.STATE, state);
						return;
					} // if
	
					
					// -----------------------------------
					// Step2. Make an URL to get Access Token, Refresh Token.
					// -----------------------------------
					
					final String tokenURL = NaverOAuth2Utils.TOKEN_REQUEST_URI + 
											"?grant_type=" + NaverOAuth2Utils.GRANT_TYPE + 
											"&client_id=" + NaverOAuth2Utils.CLIENT_ID + 
											"&client_secret=" + NaverOAuth2Utils.CLIENT_SECRET +
											"&redirect_uri=" + NaverOAuth2Utils.REDIRECT_URI +
											"&code=" + code +
											"&state=" + state;
					
					log.info("\t+ Step2 - tokenURL: {}", tokenURL);							
	
					
					// -----------------------------------
					// Step3. Send HTTP request gotten from Step2.
					// -----------------------------------
					
					HttpClient client = HttpClientBuilder.create().build();
					HttpGet get = new HttpGet(tokenURL);
					HttpResponse response = client.execute(get);
					
					log.info("\t+ Step3 - client: {}", client);
					log.info("\t+ Step3 - get: {}", get);
					log.info("\t+ Step3 - response: {}", response);
	
					
					// -----------------------------------
					// Step4. Get Access Token, Refresh Token from response gotten from Step3
					// -----------------------------------
					
					int statusCode = response.getStatusLine().getStatusCode();
					log.info("\t+ Step4 - statusCode: {}", statusCode);
					
					if(statusCode == 200) {	// OK
						
						@Cleanup BufferedReader br = new BufferedReader(
								new InputStreamReader(response.getEntity().getContent()));
											
						String line;
						StringBuilder tokenResult = new StringBuilder();
						
						while((line = br.readLine()) != null) {
							tokenResult.append(line);
						} // while
						
						String json = tokenResult.toString();
						log.info("\t+ Step4 - json: {}", json);
						
						// ---------------					
						@Cleanup("clear") JSONObject jsonObj = new JSONObject(json);
						
						String accessToken = jsonObj.getString("access_token");
						String refreshToken = jsonObj.getString("refresh_token");
						String tokenType = jsonObj.getString("token_type");
						String expiresIn = jsonObj.getString("expires_in");
						
						log.info("\t+ Step4 - accessToken({})", accessToken);
						log.info("\t+ Step4 - refreshToken({})", refreshToken);
						log.info("\t+ Step4 - tokenType({})", tokenType);
						log.info("\t+ Step4 - expiresIn({})", expiresIn);
	
						
						// -----------------------------------
						// Step5. Get User Profile from Naver with Access Token gotten from Step3
						// -----------------------------------
						
						HttpGet profileGet = new HttpGet(NaverOAuth2Utils.PROFILE_REQUEST_URI);
						profileGet.addHeader("Authorization", "Bearer " + accessToken);
						
						HttpResponse profileResponse = client.execute(profileGet);
						log.info("\t+ Step5 - profileResponse: {}", profileResponse);
						
						int profileStatusCode = profileResponse.getStatusLine().getStatusCode();	
						log.info("\t+ Step5 - profileStatusCode: {}", profileStatusCode);
						
						if(profileStatusCode == 200) {	// OK
							@Cleanup BufferedReader br2 = new BufferedReader(
										new InputStreamReader(profileResponse.getEntity().getContent())
									);
							
							StringBuilder profileBuilder = new StringBuilder();
							
							String profileLine = "";
							
							while((profileLine = br2.readLine()) != null) {
								profileBuilder.append(profileLine);
							} // while
							
							String profileJSON = profileBuilder.toString();
							log.info("\t+ Step5 - profileJSON: {}", profileJSON);
	
							// ---------------
							@Cleanup("clear") JSONObject jsonObj2 = new JSONObject(profileJSON);
							@Cleanup("clear") JSONObject responseJSONObj = jsonObj2.getJSONObject("response");
							
							String id = responseJSONObj.getString("id");
							String gender = responseJSONObj.getString("gender");
							String email = responseJSONObj.getString("email");
							String name = responseJSONObj.getString("name");
							
							log.info("\t+ Step5 - id: {}", id);
							log.info("\t+ Step5 - gender: {}", gender);
							log.info("\t+ Step5 - email: {}", email);
							log.info("\t+ Step5 - name: {}", name);
						} // if
						
					} else {
						log.info("\t+ Step4 - Not 200 OK!");
						
						return;
					} // if-else
			} catch(Exception e) {
				throw new IOException(e);
			} // try-catch
	} // service

} // end class

