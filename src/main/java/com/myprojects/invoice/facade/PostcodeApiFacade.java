package com.myprojects.invoice.facade;

import com.myprojects.invoice.apis.postcodeApi.PostcodeApiClient;
import com.myprojects.invoice.domain.dtos.PostcodeApiDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PostcodeApiFacade {

    private final PostcodeApiClient postcodeApiClient;

    public PostcodeApiDto getTownFromPostcode(String postCode) {
        return postcodeApiClient.getTown(postCode);
    }
}
