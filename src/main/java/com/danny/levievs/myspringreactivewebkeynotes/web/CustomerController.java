package com.danny.levievs.myspringreactivewebkeynotes.web;


import com.danny.levievs.myspringreactivewebkeynotes.domain.Customer;
import com.danny.levievs.myspringreactivewebkeynotes.service.CustomerService.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> getCustomerById(@PathVariable Long id) {
        return customerService.findId(id)
                .map(ResponseEntity::ok)
                .onErrorResume( ex -> {
                    if(ex instanceof IllegalArgumentException){
                        log.error("IllegalArgumentException occurred: {}", ex.getMessage());
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                    log.error("Some general error occurred: {}", ex.getMessage());
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

}
