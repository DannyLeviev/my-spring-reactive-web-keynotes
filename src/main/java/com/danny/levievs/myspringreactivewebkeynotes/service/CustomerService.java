package com.danny.levievs.myspringreactivewebkeynotes.service;


import com.danny.levievs.myspringreactivewebkeynotes.domain.Customer;
import com.danny.levievs.myspringreactivewebkeynotes.exceptions.ResourceNotFoundException;
import com.danny.levievs.myspringreactivewebkeynotes.model.AgreementDto;
import com.danny.levievs.myspringreactivewebkeynotes.model.CustomerAndAddressDto;
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


    public Mono<CustomerAndAddressDto> getCustomerById(Long id) {

        Mono<Customer> customerMono = Mono.fromCallable(() -> customerRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id)))
                .subscribeOn(Schedulers.boundedElastic());

        Mono<List<AgreementDto>> agreementMono = agreementService.getCustomerAgreements(id);

        return Mono.zip(customerMono, agreementMono, CustomerAndAddressDto::new);
    }
}
