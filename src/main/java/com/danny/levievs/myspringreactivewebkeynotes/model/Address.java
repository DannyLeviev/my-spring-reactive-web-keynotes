package com.danny.levievs.myspringreactivewebkeynotes.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Address {

    private Long id;
    private String streetName;
    private String city;
    private String country;

}
