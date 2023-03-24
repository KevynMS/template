package com.flakkeeverhuizers.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressTypeRequest {

    private String code;
    private String method;
    private int sequence;
}
