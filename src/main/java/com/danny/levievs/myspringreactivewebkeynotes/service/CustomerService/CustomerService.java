package com.danny.levievs.myspringreactivewebkeynotes.service.CustomerService;


import com.danny.levievs.myspringreactivewebkeynotes.domain.Customer;
import com.danny.levievs.myspringreactivewebkeynotes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerService {

    public final CustomerRepository customerRepository;


    public Mono<Customer> findId(Long id) {

        Customer customer = customerRepository.findById(id).orElse(null);

        if(Objects.isNull(customer)){
            return Mono.error(new IllegalArgumentException("Customer not found"));
        }

        return Mono.just(customer).delayElement(Duration.ofSeconds(2));
    }
}
