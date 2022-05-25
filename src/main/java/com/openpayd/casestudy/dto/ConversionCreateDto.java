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
public class ConversionCreateDto {

    @NotEmpty
    private String sourceCurrency;

    @NotEmpty
    private String targetCurrency;

    @NotNull
    private BigDecimal sourceAmount;
}
