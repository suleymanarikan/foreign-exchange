package com.openpayd.casestudy;

import com.openpayd.casestudy.client.response.ExchangeRateResponse;
import com.openpayd.casestudy.domain.Conversion;
import com.openpayd.casestudy.dto.ConversionCreateDto;
import com.openpayd.casestudy.dto.ConversionDto;
import com.openpayd.casestudy.dto.PageDto;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class DataFactory {

    public static ConversionCreateDto createConversionDto() {
        return ConversionCreateDto.builder().sourceAmount(BigDecimal.valueOf(100)).sourceCurrency("EUR").targetCurrency("TRY").build();
    }

    public static ExchangeRateResponse exchangeRateResponse() {
        Map<String,BigDecimal> rate = new HashMap<>();
        rate.put("TRY",BigDecimal.TEN);
        return ExchangeRateResponse.builder().base("EUR").rates(rate).success(true).build();
    }

    public static List<Conversion> conversions() {
        List conversions = new ArrayList();
        conversions.add(Conversion.builder().sourceCurrency("EUR").sourceAmount(new BigDecimal(100)).targetCurrency("TRY").targetAmount(new BigDecimal(1000)).build());
        return conversions;
    }

    public static List<ConversionDto> conversionDtos() {
        List conversionDtos = new ArrayList();
        conversionDtos.add(ConversionDto.builder().sourceCurrency("EUR").sourceAmount(new BigDecimal(100)).targetCurrency("TRY").targetAmount(new BigDecimal(1000)).build());
        return conversionDtos;
    }

    public static ConversionDto conversionDto() {
        return ConversionDto.builder().sourceAmount(BigDecimal.valueOf(100)).sourceCurrency("EUR").targetCurrency("TRY").targetAmount(BigDecimal.valueOf(1000)).build();

    }

    public static PageDto<ConversionDto> conversionPageDto() {
        return PageDto.<ConversionDto>builder().content(conversionDtos()).totalElements(1).totalPages(1).build();
    }
}
