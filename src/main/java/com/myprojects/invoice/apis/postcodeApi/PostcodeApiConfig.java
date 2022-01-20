package com.myprojects.invoice.apis.postcodeApi;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class PostcodeApiConfig {

    @Value("http://kodpocztowy.intami.pl/api/")
    private String postcodeApiEndpoint;
}