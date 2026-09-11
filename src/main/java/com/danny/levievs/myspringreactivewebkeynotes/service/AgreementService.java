package com.danny.levievs.myspringreactivewebkeynotes.service;


import com.danny.levievs.myspringreactivewebkeynotes.model.AgreementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class AgreementService {

    private final WebClient agreeWebClient;

    public Mono<List<AgreementDto>> getCustomerAgreements(Long customerId){

        log.debug("Begin calling Agreements MS, agreements/owner/{} API.", customerId);
        long startTime = System.currentTimeMillis();

        return agreeWebClient
                .get()
                .uri("owner/" + customerId)
                .retrieve()
                .bodyToFlux(AgreementDto.class)
                .collectList()
                .timeout(Duration.ofSeconds(40))  // not blocking, just safely wait till timeout
                .doFinally(responseObj -> log.debug("Elapsed Time: {}", (System.currentTimeMillis() - startTime)/100 + "sec."));
    }

}
