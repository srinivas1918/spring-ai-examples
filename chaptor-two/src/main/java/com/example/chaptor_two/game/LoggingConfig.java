package com.example.chaptor_two.game;

import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

@Configuration
public class LoggingConfig {

    @Bean
    RestClientCustomizer logbookCustomizer(
            LogbookClientHttpRequestInterceptor interceptor) {
        return restClient -> restClient.requestInterceptor(interceptor);
    }
}
