package com.openpayd.casestudy.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversionDto {

    private String sourceCurrency;

    private String targetCurrency;

    private BigDecimal sourceAmount;

    private BigDecimal targetAmount;

    private String transactionId;
}
