package com.danny.levievs.myspringreactivewebkeynotes.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
class WebClientConfig {

    @Bean
    WebClient agreementsWebClient(ApplicationProperties applicationProperties){
        return WebClient.builder().baseUrl(applicationProperties.getAgreement().getBaseUrl()).build();
    }
}
