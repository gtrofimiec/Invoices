package com.myprojects.invoice.apis;

import com.myprojects.invoice.apis.postcodeApi.PostcodeApiClient;
import com.myprojects.invoice.domain.dtos.PostcodeApiDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostcodeTestSuite {

    @Autowired
    private PostcodeApiClient postcodeApiClient;

    @Test
    public void shouldGetPostcodeFromApi() {

        //Given
        String postcode = "22-100";

        //When
        PostcodeApiDto postcodeApiDto = postcodeApiClient.getTown(postcode);
        String town = postcodeApiDto.getTown();

        //Then
        assertEquals("Chełm", town);
    }
}