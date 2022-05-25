package com.openpayd.casestudy.controller;

import com.openpayd.casestudy.DataFactory;
import com.openpayd.casestudy.client.RateClient;
import com.openpayd.casestudy.client.response.ExchangeRateResponse;
import com.openpayd.casestudy.config.ApplicationProperties;
import com.openpayd.casestudy.service.ExchangeRateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateControllerTest {

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @Mock
    private ApplicationProperties applicationProperties;

    @Mock
    private RateClient rateClient;


    @Test
    public void testGetExchangeRate() {
        ExchangeRateResponse exchangeRateResponse = DataFactory.exchangeRateResponse();
        Mockito.when(applicationProperties.getApiLayerApiKey()).thenReturn("api-key");
        Mockito.when(rateClient.getExchangeRate(Mockito.anyString(),Mockito.anyString(),Mockito.anyList())).thenReturn(exchangeRateResponse);

        BigDecimal actual = exchangeRateService.getExchangeRate("EUR","TRY");

        Assert.assertEquals(BigDecimal.TEN, actual);
    }
}
