package com.openpayd.casestudy.controller;

import com.openpayd.casestudy.service.ExchangeRateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(value = "Exchange Rate Api Documentation")
@RestController
@RequestMapping(value = "/exchange-rates")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping
    @ApiOperation(value = "Get current exchange rate")
    public ResponseEntity getExchangeRate(@RequestParam String sourceCurrency, @RequestParam String targetCurrency) {
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(sourceCurrency,targetCurrency);
        return new ResponseEntity(exchangeRate, HttpStatus.OK);
    }

}
