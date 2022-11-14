package gr.uAegean.mockDataGenerator.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uAegean.mockDataGenerator.model.PameasPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DBProxyServiceImpl {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenServiceImpl accessTokenService;


    public List<PameasPerson> getPassengerDetails() {
        String uri
                = System.getenv("DB_PROXY_URI") + "getPassengers";

        HttpHeaders headers = new HttpHeaders();
        String bearer = "Bearer " + accessTokenService.getAccessToken().get();
        headers.set("Authorization", bearer);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseString = restTemplate.exchange(
                uri, HttpMethod.GET, requestEntity, String.class);
        ObjectMapper mapper = new ObjectMapper().configure(JsonParser.Feature.IGNORE_UNDEFINED, true);

        try {
            return Arrays.asList(mapper.readValue(responseString.getBody(), PameasPerson[].class));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        return null;
    }


    public Optional<PameasPerson> getSinglePassengerDetails(String hashedMacAddress) {
        String uri
                = System.getenv("DB_PROXY_URI") + "getPassengers";
        HttpHeaders headers = new HttpHeaders();
        String bearer = "Bearer " + accessTokenService.getAccessToken().get();
        headers.set("Authorization", bearer);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseString = restTemplate.exchange(
                uri, HttpMethod.GET, requestEntity, String.class);
        ObjectMapper mapper = new ObjectMapper().configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        Optional<PameasPerson> result = Optional.empty();

        try {
            PameasPerson[] persons = mapper.readValue(responseString.getBody(), PameasPerson[].class);
            return Arrays.stream(persons).filter(pameasPerson ->
                    pameasPerson.getNetworkInfo().getDeviceInfoList().get(0).getHashedMacAddress().equals(hashedMacAddress)).findFirst();
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    //updatePerson
    public void updatePerson(PameasPerson person) {
        String url
                = System.getenv("DB_PROXY_URI") + "updatePerson";
        HttpHeaders headers = new HttpHeaders();
        String bearer = "Bearer " + accessTokenService.getAccessToken().get();
        headers.set("Authorization", bearer);
        HttpEntity<PameasPerson> request = new HttpEntity<>(person, headers);
        String response = restTemplate.postForObject(url, request, String.class);
        log.info("updated person {}", response);
    }


}
