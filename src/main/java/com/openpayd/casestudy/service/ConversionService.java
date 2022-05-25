package com.openpayd.casestudy.service;

import com.openpayd.casestudy.domain.Conversion;
import com.openpayd.casestudy.dto.ConversionCreateDto;
import com.openpayd.casestudy.dto.ConversionDto;
import com.openpayd.casestudy.dto.PageDto;
import com.openpayd.casestudy.exception.CaseStudyRuntimeException;
import com.openpayd.casestudy.exception.ErrorCode;
import com.openpayd.casestudy.repository.ConversionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ConversionService {

    private final ConversionRepository conversionRepository;
    private final ExchangeRateService exchangeRateService;

    public ConversionDto convert(ConversionCreateDto conversionCreateDto) {
        BigDecimal rate = exchangeRateService.getExchangeRate(conversionCreateDto.getSourceCurrency(), conversionCreateDto.getTargetCurrency());
        BigDecimal targetAmount = rate.multiply(conversionCreateDto.getSourceAmount());

        Conversion conversion = Conversion.builder().sourceCurrency(conversionCreateDto.getSourceCurrency())
                .targetCurrency(conversionCreateDto.getTargetCurrency()).exchangeRate(rate)
                .sourceAmount(conversionCreateDto.getSourceAmount()).targetAmount(targetAmount)
                .transactionId(UUID.randomUUID().toString()).transactionDate(LocalDate.now()).build();

        conversionRepository.save(conversion);
        return ConversionDto.builder().transactionId(conversion.getTransactionId()).targetAmount(targetAmount).build();

    }

    public PageDto getConversions(String transactionId, LocalDate transactionDate, Integer offset, Integer limit) {
        if (transactionId == null && transactionDate == null){
            throw new CaseStudyRuntimeException(ErrorCode.MISSING_TRANSACTION_ID_AND_DATE);
        }

        PageRequest pageRequest = PageRequest.of(offset, limit);
        Page<Conversion> page = conversionRepository.findConversionByTransactionIdOrTransactionDateOrderByCreatedAt(transactionId, transactionDate, pageRequest);

        return PageDto.builder().totalPages(page.getTotalPages()).totalElements(page.getTotalElements())
                .content(page.getContent().stream().map(content -> ConversionDto.builder().sourceCurrency(content.getSourceCurrency()).sourceAmount(content.getSourceAmount())
                        .targetCurrency(content.getTargetCurrency()).targetAmount(content.getTargetAmount()).transactionId(content.getTransactionId()).build()
                ).collect(Collectors.toList())).build();
    }
}
