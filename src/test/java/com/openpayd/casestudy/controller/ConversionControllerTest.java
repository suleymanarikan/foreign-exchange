package com.openpayd.casestudy.controller;

import com.openpayd.casestudy.DataFactory;
import com.openpayd.casestudy.dto.ConversionCreateDto;
import com.openpayd.casestudy.dto.ConversionDto;
import com.openpayd.casestudy.dto.PageDto;
import com.openpayd.casestudy.service.ConversionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class ConversionControllerTest {

    @InjectMocks
    private ConversionController conversionController;

    @Mock
    private ConversionService conversionService;

    @Test
    public void testConvert() {
        ConversionDto conversionDto = DataFactory.conversionDto();
        Mockito.when(conversionService.convert(Mockito.any(ConversionCreateDto.class))).thenReturn(conversionDto);

        ConversionCreateDto conversionCreateDto = DataFactory.createConversionDto();
        ResponseEntity<ConversionDto> actual = conversionController.convert(conversionCreateDto);

        Mockito.verify(conversionService).convert(Mockito.any(ConversionCreateDto.class));
        Assert.assertEquals(new BigDecimal(1000), actual.getBody().getTargetAmount());
    }


    @Test
    public void testGetConversions() {
        PageDto<ConversionDto> conversionDto = DataFactory.conversionPageDto();
        Mockito.when(conversionService.getConversions(Mockito.isNull(),Mockito.any(LocalDate.class),Mockito.anyInt(),Mockito.anyInt())).thenReturn(conversionDto);

        ResponseEntity<PageDto> actual = conversionController.getConversions(null,LocalDate.now(),50,0);

        Mockito.verify(conversionService).getConversions(Mockito.isNull(),Mockito.any(LocalDate.class),Mockito.eq(0),Mockito.eq(50));
        Assert.assertNotNull(actual.getBody().getContent());
    }

}
