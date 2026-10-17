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

    public Mono<CustomerDto> createCustomer(CustomerDto request) {
        return Mono.fromCallable(() -> {
                    Customer customer = Customer.builder()
                            .firstName(request.customer().getFirstName())
                            .lastName(request.customer().getLastName())
                            .email(request.customer().getEmail())
                            .phoneNumber(request.customer().getPhoneNumber())
                            .dateOfBirth(request.customer().getDateOfBirth())
                            .active(true)
                            .build();
                    return customerRepository.save(customer);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(customer -> new CustomerDto(customer, List.of()));
    }

    public Mono<CustomerDto> updateCustomer(Long id, CustomerDto request) {
        return Mono.fromCallable(() -> {
                    Customer customer = customerRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
                    customer.setFirstName(request.customer().getFirstName());
                    customer.setLastName(request.customer().getLastName());
                    customer.setEmail(request.customer().getEmail());
                    customer.setPhoneNumber(request.customer().getPhoneNumber());
                    customer.setDateOfBirth(request.customer().getDateOfBirth());
                    return customerRepository.save(customer);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(customer -> new CustomerDto(customer, List.of()));
    }

    public Mono<Void> deleteCustomer(Long id) {
        return Mono.<Void>fromRunnable(() -> {
                    if (!customerRepository.existsById(id)) {
                        throw new ResourceNotFoundException("Customer not found with id " + id);
                    }
                    customerRepository.deleteById(id);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

}
