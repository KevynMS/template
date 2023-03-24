package com.flakkeeverhuizers.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryRequest {

    private String name;
    private String countryCode;
    private String flagIcon;
}
