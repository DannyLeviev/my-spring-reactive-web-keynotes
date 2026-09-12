package com.danny.levievs.myspringreactivewebkeynotes.web;


import com.danny.levievs.myspringreactivewebkeynotes.model.CustomerAndAddressDto;
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
    public Mono<ResponseEntity<CustomerAndAddressDto>> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok);
    }

}
