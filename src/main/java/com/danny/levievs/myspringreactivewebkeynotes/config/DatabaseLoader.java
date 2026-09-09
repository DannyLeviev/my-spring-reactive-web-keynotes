package com.danny.levievs.myspringreactivewebkeynotes.config;

import com.danny.levievs.myspringreactivewebkeynotes.domain.Customer;
import com.danny.levievs.myspringreactivewebkeynotes.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DatabaseLoader {

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                List<Customer> sampleCustomers = List.of(
                        Customer.builder()
                                .firstName("James")
                                .lastName("Miller")
                                .email("james.miller@example.com")
                                .phoneNumber("+1-204-555-0101")
                                .dateOfBirth(LocalDate.of(1985, 4, 12))
                                .active(true)
                                .build(),
                        Customer.builder()
                                .firstName("Sophia")
                                .lastName("Chen")
                                .email("sophia.chen@example.com")
                                .phoneNumber("+1-604-555-0102")
                                .dateOfBirth(LocalDate.of(1992, 8, 23))
                                .active(true)
                                .build(),
                        Customer.builder()
                                .firstName("Liam")
                                .lastName("Smith")
                                .email("liam.smith@example.com")
                                .phoneNumber("+1-250-555-0103")
                                .dateOfBirth(LocalDate.of(1978, 11, 5))
                                .active(true)
                                .build(),
                        Customer.builder()
                                .firstName("Emma")
                                .lastName("Johnson")
                                .email("emma.johnson@example.com")
                                .phoneNumber("+1-204-555-0104")
                                .dateOfBirth(LocalDate.of(1995, 1, 30))
                                .active(false)
                                .build(),
                        Customer.builder()
                                .firstName("Noah")
                                .lastName("Williams")
                                .email("noah.williams@example.com")
                                .phoneNumber("+1-604-555-0105")
                                .dateOfBirth(LocalDate.of(1988, 6, 17))
                                .active(true)
                                .build(),
                        Customer.builder()
                                .firstName("Olivia")
                                .lastName("Brown")
                                .email("olivia.brown@example.com")
                                .phoneNumber("+1-250-555-0106")
                                .dateOfBirth(LocalDate.of(2001, 9, 14))
                                .active(true)
                                .build(),
                        Customer.builder()
                                .firstName("Lucas")
                                .lastName("Davis")
                                .email("lucas.davis@example.com")
                                .phoneNumber("+1-204-555-0107")
                                .dateOfBirth(LocalDate.of(1983, 3, 29))
                                .active(false)
                                .build(),
                        Customer.builder()
                                .firstName("Ava")
                                .lastName("Taylor")
                                .email("ava.taylor@example.com")
                                .phoneNumber("+1-604-555-0108")
                                .dateOfBirth(LocalDate.of(1999, 12, 8))
                                .active(true)
                                .build(),
                        Customer.builder()
                                .firstName("Ethan")
                                .lastName("Anderson")
                                .email("ethan.anderson@example.com")
                                .phoneNumber("+1-250-555-0109")
                                .dateOfBirth(LocalDate.of(1990, 7, 21))
                                .active(true)
                                .build(),
                        Customer.builder()
                                .firstName("Mia")
                                .lastName("Wilson")
                                .email("mia.wilson@example.com")
                                .phoneNumber("+1-204-555-0110")
                                .dateOfBirth(LocalDate.of(1996, 5, 2))
                                .active(true)
                                .build()
                );

                repository.saveAll(sampleCustomers);
            }
        };
    }
}
