package com.myprojects.invoice.apis.postcodeApi;

import com.myprojects.invoice.domain.dtos.PostcodeApiDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostcodeApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostcodeApiClient.class);

    private final RestTemplate restTemplate;
    private final PostcodeApiConfig postcodeApiConfig;

    public PostcodeApiDto getTown(String postcode) {
        URI url = UriComponentsBuilder.fromHttpUrl(postcodeApiConfig.getPostcodeApiEndpoint())
                .path(postcode)
                .build()
                .encode()
                .toUri();

        try {
            PostcodeApiDto[] townsResponse = restTemplate.getForObject(url, PostcodeApiDto[].class);
            return Optional.ofNullable(townsResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(t -> t.getTown().equals(t.getDistrictCommune()))
                    .findFirst()
                    .get();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}