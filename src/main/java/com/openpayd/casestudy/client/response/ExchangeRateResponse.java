package com.openpayd.casestudy.client.response;

import lombok.*;
import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateResponse {

    private String base;
    private Map<String, BigDecimal> rates;
    private Boolean success;

}
