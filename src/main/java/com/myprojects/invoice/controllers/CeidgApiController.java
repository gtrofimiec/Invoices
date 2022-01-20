package com.myprojects.invoice.controllers;

import com.myprojects.invoice.domain.dtos.CeidgApiDto;
import com.myprojects.invoice.exceptions.CeidgDataNotFoundException;
import com.myprojects.invoice.facade.CeidgApiFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("v1/invoices")
public class CeidgApiController {

    private final CeidgApiFacade ceidgApiFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/getData/{nip}")
    public CeidgApiDto getCustomerData(@PathVariable("nip") String nip) throws CeidgDataNotFoundException {
        return ceidgApiFacade.getCustomerData(nip);
    }
}