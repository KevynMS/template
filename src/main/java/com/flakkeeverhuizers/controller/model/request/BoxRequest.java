package com.flakkeeverhuizers.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxRequest {

    private String code;
    private String name;
    private BigDecimal volume;
    private Boolean fragile;
}
