package com.danny.levievs.myspringreactivewebkeynotes.service;


import com.danny.levievs.myspringreactivewebkeynotes.domain.Customer;
import com.danny.levievs.myspringreactivewebkeynotes.exceptions.ResourceNotFoundException;
import com.danny.levievs.myspringreactivewebkeynotes.model.AgreementDto;
import com.danny.levievs.myspringreactivewebkeynotes.model.CustomerDto;
import com.danny.levievs.myspringreactivewebkeynotes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AgreementService agreementService;


    public Mono<CustomerDto> getCustomerById(Long id, boolean agreementsIncluded) {

        Mono<Customer> customerMono = Mono.fromCallable(() -> customerRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id)))
                .subscribeOn(Schedulers.boundedElastic());

            Mono<List<AgreementDto>> agreementMono =
                    agreementsIncluded ? agreementService.getCustomerAgreements(id) : Mono.just(List.of());

        return Mono.zip(customerMono, agreementMono, CustomerDto::new);
    }
}
