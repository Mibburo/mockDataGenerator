package gr.uAegean.mockDataGenerator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uAegean.mockDataGenerator.model.OAuthAccessTokenTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Slf4j
@Service
public class AccessTokenServiceImpl   {

    @Autowired
    private RestTemplate restTemplate;


    public Optional<String> getAccessToken() {
        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        String oauthUri = System.getenv("OAUTH_URI");
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("client_id", clientId);
            map.add("client_secret", clientSecret);
            map.add("grant_type", "client_credentials");
            map.add("scope", "openid");
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

            String json = restTemplate.postForEntity(oauthUri, request, String.class).getBody();
            ObjectMapper mapper = new ObjectMapper();
            OAuthAccessTokenTO token = mapper.readValue(json, OAuthAccessTokenTO.class);

            return
                    Optional.of(token.getAccessToken());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return Optional.empty();
    }
}
