package kr.or.ddit.common.zoomapi.component;

import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import kr.or.ddit.common.zoomapi.dto.ZoomAuthResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ZoomAuthenticationHelper {
  @Value("${spring.zoom.oauth2.client-id}")
  private String zoomClientId;
  
  @Value("${spring.zoom.oauth2.client-secret}")
  private String zoomClientSecret;
  
  @Value("${spring.zoom.oauth2.issuer}")
  private String zoomIssueUrl;
  
  @Value("${spring.zoom.oauth2.account-id}")
  private String zoomAccountId;
  
  private ZoomAuthResponse zoomAuthResponse;
  
  private long tokenExpiryTime;
  
  public synchronized String getAccessToken() throws Exception {
    if(this.zoomAuthResponse == null || checkIfTokenWillExpire()) {
      fetchToken();
    }
    return this.zoomAuthResponse.getAccessToken();
  }
  
  private boolean checkIfTokenWillExpire() {
    Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
    long differenceMillis = this.tokenExpiryTime - now.getTimeInMillis();
    
    if(differenceMillis < 0 || TimeUnit.MILLISECONDS.toMinutes(differenceMillis)<20) {
      return true;
    }
    return false;
        
  }
  
  private void fetchToken() throws Exception{
    RestTemplate restTemplate = new RestTemplate();
    
    String credentials = zoomClientId + ":" + zoomClientSecret;
    String encodedCredentials = new String(Base64.getEncoder().encodeToString(credentials.getBytes()));
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
    httpHeaders.add("Authorization", "Basic " + encodedCredentials);
    httpHeaders.add("Host", "zoom.us");
    
    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
    map.add("grant_type", "account_credentials");
    map.add("account_id", zoomAccountId);
    
    HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);
    try {
      this.zoomAuthResponse = restTemplate.exchange(
            zoomIssueUrl, HttpMethod.POST, httpEntity, ZoomAuthResponse.class
          ).getBody();
    }catch(HttpClientErrorException e) {
      ResponseEntity<String> errorResponse = new ResponseEntity<String>(e.getResponseBodyAsString(), e.getStatusCode());
      throw new Exception(
            String 
              .format("Unable to get authentication token due to %s. Response code : %d",
                      errorResponse.getBody(),
                      errorResponse.getStatusCode().value()
                      )
          );
    }
    Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
    this.tokenExpiryTime = now.getTimeInMillis() + (this.zoomAuthResponse.getExpiresIn() - 10) * 1000;
  }
}
