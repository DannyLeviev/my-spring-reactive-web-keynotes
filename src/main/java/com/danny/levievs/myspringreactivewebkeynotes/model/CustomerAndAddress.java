package com.danny.levievs.myspringreactivewebkeynotes.model;

import com.danny.levievs.myspringreactivewebkeynotes.domain.Customer;

import java.util.List;


public record CustomerAndAddress(Customer customer, List<AgreementDto> agreement) {

}
