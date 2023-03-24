package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressTypeResponse {

    private String id;
    private String addressTypeId;
    private String code;
    private String method;
    private int sequence;
}
