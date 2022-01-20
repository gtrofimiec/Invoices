package com.myprojects.invoice.facade;

import com.myprojects.invoice.apis.ceidgapi.CeidgApiClient;
import com.myprojects.invoice.domain.dtos.CeidgApiDto;
import com.myprojects.invoice.exceptions.CeidgDataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CeidgApiFacade {

    private final CeidgApiClient ceidgApiClient;

    public CeidgApiDto getCustomerData(String nip) throws CeidgDataNotFoundException {
        return ceidgApiClient.getCeidgData(nip);
    }
}
