package com.myprojects.invoice.controllers;

import com.myprojects.invoice.domain.dtos.PostcodeApiDto;
import com.myprojects.invoice.exceptions.CeidgDataNotFoundException;
import com.myprojects.invoice.facade.PostcodeApiFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("v1/invoices")
public class PostcodeApiController {

    private final PostcodeApiFacade postcodeApiFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/getTown/{postCode}")
    public PostcodeApiDto getTownFromPostcode(@PathVariable("postCode") String postCode) throws CeidgDataNotFoundException {
        return postcodeApiFacade.getTownFromPostcode(postCode);
    }
}
