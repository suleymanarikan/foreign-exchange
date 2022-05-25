package com.openpayd.casestudy.service;

import com.openpayd.casestudy.DataFactory;
import com.openpayd.casestudy.domain.Conversion;
import com.openpayd.casestudy.dto.ConversionCreateDto;
import com.openpayd.casestudy.dto.ConversionDto;
import com.openpayd.casestudy.dto.PageDto;
import com.openpayd.casestudy.exception.CaseStudyRuntimeException;
import com.openpayd.casestudy.repository.ConversionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ConversionServiceTest {

    @InjectMocks
    private ConversionService conversionService;

    @Mock
    private ConversionRepository conversionRepository;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Test
    public void testConvert() {
        Mockito.when(exchangeRateService.getExchangeRate(Mockito.anyString(),Mockito.anyString())).thenReturn(BigDecimal.TEN);

        ConversionCreateDto conversionCreateDto = DataFactory.createConversionDto();
        ConversionDto actual = conversionService.convert(conversionCreateDto);

        Mockito.verify(conversionRepository).save(Mockito.any(Conversion.class));
        Assert.assertEquals(new BigDecimal(1000), actual.getTargetAmount());
    }


    @Test(expected=CaseStudyRuntimeException.class)
    public void testGetConversions_TransactionIdOrDateNotGiven() {
        conversionService.getConversions(null, null, 0, 50);
    }

    @Test
    public void testGetConversions() {
        List<Conversion> conversions = DataFactory.conversions();
        Mockito.when(conversionRepository.findConversionByTransactionIdOrTransactionDateOrderByCreatedAt(Mockito.isNull(),Mockito.any(LocalDate.class), Mockito.any(PageRequest.class)))
                .thenReturn(new PageImpl<>(conversions));

        PageDto actual = conversionService.getConversions(null, LocalDate.now(), 0, 50);

        Mockito.verify(conversionRepository).findConversionByTransactionIdOrTransactionDateOrderByCreatedAt(Mockito.isNull(),Mockito.any(LocalDate.class), Mockito.any(PageRequest.class));
        assertNotNull(actual.getContent());
    }

}
