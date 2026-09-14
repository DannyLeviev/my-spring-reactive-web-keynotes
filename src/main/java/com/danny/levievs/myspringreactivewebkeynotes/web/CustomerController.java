package com.danny.levievs.myspringreactivewebkeynotes.web;


import com.danny.levievs.myspringreactivewebkeynotes.model.CustomerDto;
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
    public Mono<ResponseEntity<CustomerDto>> getCustomerById(@PathVariable Long id,
                                                             @RequestParam(name = "agreementsIncluded", defaultValue = "false") boolean agreementsIncluded) {
        return customerService.getCustomerById(id, agreementsIncluded)
                .map(ResponseEntity::ok);
    }

}
