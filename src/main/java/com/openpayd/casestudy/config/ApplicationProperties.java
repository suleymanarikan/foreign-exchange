package com.openpayd.casestudy.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApplicationProperties {

    @Value("${apilayer.fixer.api.key}")
    private String apiLayerApiKey;
}
