package com.myprojects.invoice.apis.ceidgapi;

import com.myprojects.invoice.domain.dtos.CeidgApiDataListDto;
import com.myprojects.invoice.domain.dtos.CeidgApiDto;
import com.myprojects.invoice.domain.dtos.CustomerDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Component
public class CeidgApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CeidgApiClient.class);

    private final RestTemplate restTemplate;
    private final CeidgApiConfig ceidgApiConfig;

    public CeidgApiClient(RestTemplate restTemplate, CeidgApiConfig ceidgApiConfig) {
        this.restTemplate = restTemplate;
        this.ceidgApiConfig = ceidgApiConfig;
    }

    public CeidgApiDto getCeidgData(String nip, String endpoint, String token) {

        URI url = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("nip", nip)
                .build()
                .encode()
                .toUri();

        try {
            ResponseEntity<CeidgApiDataListDto> foundData = restTemplate.exchange(url, HttpMethod.GET,
                    new HttpEntity<>(createHeaders(token)), CeidgApiDataListDto.class);

            CustomerDataDto obj = Objects.requireNonNull(foundData.getBody()).getCustomerDataDto().get(0);

            return new CeidgApiDto(
                    obj.getFullName(),
                    obj.getAddressDto().getStreet(),
                    obj.getAddressDto().getBuilding(),
                    obj.getAddressDto().getPostCode(),
                    obj.getAddressDto().getTown()
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    HttpHeaders createHeaders(String token){
        return new HttpHeaders() {{
            String authHeader = "Bearer " + token;
            set("Authorization", authHeader);
        }};
    }
}