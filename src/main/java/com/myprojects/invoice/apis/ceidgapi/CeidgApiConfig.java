package com.myprojects.invoice.apis.ceidgapi;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CeidgApiConfig {

    @Value("https://test-dane.biznes.gov.pl/api/ceidg/v1/firma?")
    private String ceidgApiTestEndpoint;
    @Value("https://dane.biznes.gov.pl/api/ceidg/v1/firma?")
    private String ceidgApiProdEndpoint;
    @Value("${ceidg.api.test.token}")
    private String tokenTest;
    @Value("${ceidg.api.prod.token}")
    private String token;
}