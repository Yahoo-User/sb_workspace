package org.zerock.myapp.servlet.kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serial;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@Slf4j

@NoArgsConstructor

@WebServlet("/RedirectionUriFromKakaoLogin")
public class KakaoCallbackServlet extends HttpServlet {
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
					log.info("\t+ Step1 - code: {}", code);
					
					// -----------------------------------
					// Step2. Make an URL to get Access Token, Refresh Token.
					// -----------------------------------
					Objects.requireNonNull(code);
					
					final String tokenURL = KakaoOAuth2Utils.TOKEN_URI;
					final String queryString =
							"code=" + code +
							"&client_id=" + KakaoOAuth2Utils.CLIENT_ID +
							"&redirect_uri=" + KakaoOAuth2Utils.REDIRECT_URI +
							"&grant_type=" + KakaoOAuth2Utils.GRANT_TYPE; 
					
					log.info("\t+ Step2 - tokenURL : {}", tokenURL);
					log.info("\t+ Step2 - queryString : {}", queryString);
					
					// -----------------------------------
					// Step3. Send HTTP request gotten from Step2.
					// -----------------------------------
					URL tokenUrl = new URL(tokenURL);
					Objects.requireNonNull(tokenUrl);
					
					URLConnection tokenUrlConn = tokenUrl.openConnection();
					log.info("\t+ Step3 - tokenUrlConn: {}", tokenUrlConn);
					Objects.requireNonNull(tokenUrlConn);
					
					if(tokenUrlConn instanceof HttpURLConnection httpTokenUrlConn) {
						log.info("\t+ Step3 - httpUrlConn: {}", httpTokenUrlConn);

						// -------------
						httpTokenUrlConn.setRequestMethod("POST");
						httpTokenUrlConn.setDoOutput(true);

						// -------------
						@Cleanup OutputStream os = httpTokenUrlConn.getOutputStream();
						os.write(queryString.getBytes());
						os.flush();
						
						// -------------
						@Cleanup InputStream is = httpTokenUrlConn.getInputStream();
						@Cleanup BufferedReader br = new BufferedReader(new InputStreamReader(is));
						
						// -----------------------------------
						// Step4. Get Access Token, Refresh Token from response gotten from Step3
						// -----------------------------------
						StringBuffer sb = new StringBuffer();
						
						String line;
						while((line = br.readLine()) != null) {
							sb.append(line);
						} // while
						
						final String responseJSON = sb.toString();
						log.info("\t+ Step4 - responseJSON: {}", responseJSON);
						
						// -------------
						
						/*
						{
						    "access_token": "zt34f-Fe7sYPXeCFtkOcsWilxJ6eblwQAAAAAQo9dZoAAAGQQqx2y63XznpenZPe",
						    "token_type": "bearer",
						    "refresh_token": "UmmN2Cy1K7v6uliZpWnLrs69gc9cmDxbAAAAAgo9dZoAAAGQQqx2ya3XznpenZPe",
						    "id_token": "eyJraWQiOiI5ZjI1MmRhZGQ1ZjIzM2Y5M2QyZmE1MjhkMTJmZWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJjMzBlZGRjZjhiMTc1ZDQ5M2YwNDc4M2ZjZGYzZWUyNiIsInN1YiI6IjM1OTE5NDIxMTkiLCJhdXRoX3RpbWUiOjE3MTkxMDU1MTcsImlzcyI6Imh0dHBzOi8va2F1dGgua2FrYW8uY29tIiwiZXhwIjoxNzE5MTI3MTE3LCJpYXQiOjE3MTkxMDU1MTd9.fa_HM-h17H_3DTbURqpw9MJ0tMSesUCWiveZG0Qlw0G4wjh2MRDCa3r2M3RKr-ktLO1GeU4wKXhEq0I-3HNy9QXI8DLnct0vSVF_WLQYA99t8hQumCVFYnKMIDd-bgW0SjpqNbOBM6iM0divFzEVNUFdqRbOS7CqfvnXV5Oy1FnI0gU-BL1OMc7mnh8ErfvQCGuT8X7EQnYlYiwnIE6gUp7v8TzDtPtXdh2dOeDD1eBerupmle47hv1dGNq4jFcN_lLvDUATILqAaB8TuLb5TYaCGMKT76g8oAkDi-fiVOkaWixzOoexL2AXgHzQziwzlcswhwb6dPjYnFlETs3AGQ",
						    "expires_in": 21599,
						    "scope": "openid",
						    "refresh_token_expires_in": 5183999
						}
						*/
						
						JSONObject jsonObj = new JSONObject(responseJSON);
						
						final String accessToken = jsonObj.getString("access_token");
						final String tokenType = jsonObj.getString("token_type");
						final String refreshToken = jsonObj.getString("refresh_token");
						final String idToken = jsonObj.getString("id_token");
						final Integer expiresIn = jsonObj.getInt("expires_in");
						final String scope = jsonObj.getString("scope");
						final Integer refreshTokenExpiresIn = jsonObj.getInt("refresh_token_expires_in");
						
						log.info("\t+ Step4 - accessToken({})", accessToken);
						log.info("\t+ Step4 - tokenType({})", tokenType);
						log.info("\t+ Step4 - refreshToken({})", refreshToken);
						log.info("\t+ Step4 - idToken({})", idToken);
						log.info("\t+ Step4 - expiresIn({})", expiresIn);
						log.info("\t+ Step4 - scope({})", scope);
						log.info("\t+ Step4 - refreshTokenExpiresIn({})", refreshTokenExpiresIn);

						// -----------------------------------
						// Step5. Get User Profile from Naver with Access Token gotten from Step3
						// -----------------------------------
						URL profileUrl = new URL(KakaoOAuth2Utils.PROFILE_URI);
						Objects.requireNonNull(profileUrl);
						
						URLConnection profileUrlConn = profileUrl.openConnection();
						Objects.requireNonNull(profileUrlConn);
						
						if(profileUrlConn instanceof HttpURLConnection httpProfileUrlConn) {
							httpProfileUrlConn.setRequestMethod("GET");
							httpProfileUrlConn.setRequestProperty("Authorization", "Bearer " + accessToken);
							
							@Cleanup InputStream is2 = httpProfileUrlConn.getInputStream();
							@Cleanup BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
							
							StringBuffer sb2 = new StringBuffer();
							
							String line2;
							while((line2 = br2.readLine()) != null) {
								sb2.append(line2);
							} // while
							
							String profileJSON = sb2.toString();
							log.info("\t+ Step5 - profileJSON: {}", profileJSON);
						} // if
						
					} // if
			} catch(Exception e) {
				throw new IOException(e);
			} // try-catch
	} // service

} // end class

