package com.openpayd.casestudy.service;

import com.openpayd.casestudy.client.RateClient;
import com.openpayd.casestudy.client.response.ExchangeRateResponse;
import com.openpayd.casestudy.config.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ApplicationProperties applicationProperties;
    private final RateClient rateClient;

    public BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency) {
        List<String> symbols = Arrays.asList(targetCurrency);
        ExchangeRateResponse response = rateClient.getExchangeRate(applicationProperties.getApiLayerApiKey(),sourceCurrency,symbols);
        return response.getRates().get(targetCurrency);
    }
}
