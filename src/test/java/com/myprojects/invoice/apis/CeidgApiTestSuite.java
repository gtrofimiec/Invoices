package com.myprojects.invoice.apis;

import com.myprojects.invoice.apis.ceidgapi.CeidgApiClient;
import com.myprojects.invoice.apis.ceidgapi.CeidgApiConfig;
import com.myprojects.invoice.domain.dtos.CeidgApiDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CeidgApiTestSuite {

    @Autowired
    private CeidgApiClient ceidgApiClient;
    @Autowired
    private CeidgApiConfig ceidgApiConfig;

    @Test
    public void shouldGetTestDataFromCeidgApi() {

        //Given
        String nip = "2367852376";
        String endpoint = ceidgApiConfig.getCeidgApiTestEndpoint();
        String token = ceidgApiConfig.getTestToken();

        //When
        CeidgApiDto ceidgApiDto = ceidgApiClient.getCeidgData(nip, endpoint, token);
        String fullName = ceidgApiDto.getFullName();
        String building = ceidgApiDto.getBuilding();
        String street = ceidgApiDto.getStreet();
        String postCode = ceidgApiDto.getPostCode();
        String town = ceidgApiDto.getTown();

        //Then
        assertEquals("Adam IntegracjaMGMF - Zmiana", fullName);
        assertEquals("1", building);
        assertEquals("ul. Zwierzyniecka", street);
        assertEquals("15-333", postCode);
        assertEquals("Białystok", town);
    }
}