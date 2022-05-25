package com.openpayd.casestudy.controller;

import com.openpayd.casestudy.dto.ConversionCreateDto;
import com.openpayd.casestudy.dto.ConversionDto;
import com.openpayd.casestudy.dto.PageDto;
import com.openpayd.casestudy.service.ConversionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Api(value = "Conversion Api documentation")
@RestController
@RequestMapping(value = "/conversions")
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionService conversionService;

    @PostMapping
    @ApiOperation(value = "Create conversion")
    ResponseEntity convert(@Valid @RequestBody ConversionCreateDto conversionCreateDto){
        ConversionDto result = conversionService.convert(conversionCreateDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Get conversions by filter")
    public ResponseEntity getConversions(@RequestParam(required = false) String transactionId, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate transactionDate,
                                         @RequestParam(defaultValue = "50") Integer limit, @RequestParam(defaultValue = "0") Integer offset) {
        PageDto result = conversionService.getConversions(transactionId,transactionDate,offset,limit);
        return new ResponseEntity(result, HttpStatus.OK);
    }


}
