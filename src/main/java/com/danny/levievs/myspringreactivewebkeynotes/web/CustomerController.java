package com.danny.levievs.myspringreactivewebkeynotes.web;


import com.danny.levievs.myspringreactivewebkeynotes.model.CustomerAndAddress;
import com.danny.levievs.myspringreactivewebkeynotes.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerAndAddress>> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
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
