package com.danny.levievs.myspringreactivewebkeynotes.web;


import com.danny.levievs.myspringreactivewebkeynotes.model.CustomerDto;
import com.danny.levievs.myspringreactivewebkeynotes.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public Mono<ResponseEntity<CustomerDto>> getCustomerById(@PathVariable Long id,
                                                             @RequestParam(name = "agreementsIncluded", defaultValue = "false") boolean agreementsIncluded) {
        return customerService.getCustomerById(id, agreementsIncluded)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<CustomerDto>> createCustomer(@RequestBody CustomerDto request) {
        return customerService.createCustomer(request)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<CustomerDto>> updateCustomer(@PathVariable Long id,
                                                            @RequestBody CustomerDto request) {
        return customerService.updateCustomer(id, request)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id)
                .thenReturn(ResponseEntity.noContent().<Void>build());
    }

}
