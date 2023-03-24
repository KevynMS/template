package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.CountryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchAddressRequest {

    private CountryRequest country;

    private String street;

    private String houseNumber;

    private String zipcode;

    private String city;

    private String addressTypeCode;
}
