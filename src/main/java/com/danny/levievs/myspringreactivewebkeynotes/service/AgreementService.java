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

    private final WebClient agreementsWebClient;

    public Mono<List<AgreementDto>> getCustomerAgreements(Long customerId){

        log.debug("Calling Agreements MS, agreements/owner/{} API.", customerId);
        long startTime = System.currentTimeMillis();

        return agreementsWebClient
                .get()
                .uri("owner/" + customerId)
                .retrieve()
                .bodyToFlux(AgreementDto.class)
                .collectList()
                .timeout(Duration.ofSeconds(40))  // not blocking, just safely wait till timeout
                .doFinally(responseObj -> log.info("Elapsed Time: {}", (System.currentTimeMillis() - startTime)/100 + "sec."));
    }

}
