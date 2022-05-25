package com.openpayd.casestudy.client;

import com.openpayd.casestudy.client.response.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "rate-client", url = "${apilayer.fixer.api.url}")
public interface RateClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest")
    ExchangeRateResponse getExchangeRate(@RequestHeader String apikey, @RequestParam String base, @RequestParam List<String> symbols);

}
