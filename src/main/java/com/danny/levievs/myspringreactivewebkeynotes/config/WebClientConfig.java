package com.danny.levievs.myspringreactivewebkeynotes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class WebClientConfig {

    @Bean
    WebClient agreementsWebClient(ApplicationProperties applicationProperties){
        return WebClient.builder()
                .baseUrl(applicationProperties.getAgreement().getBaseUrl())
                .build();
    }
}
