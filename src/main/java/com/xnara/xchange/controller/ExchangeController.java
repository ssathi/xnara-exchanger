package com.xnara.xchange.controller;

import com.xnara.xchange.controller.dto.ExchangeRequest;
import com.xnara.xchange.controller.dto.ExchangeResponse;
import com.xnara.xchange.exception.InvalidRequestException;
import com.xnara.xchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

    @Autowired
    private ExchangeService service;

    @PostMapping("/xnara/xchanger/rate")
    public ExchangeResponse getRate(@RequestBody ExchangeRequest request) throws InvalidRequestException {
        return service.getRate(request);
    }
}
