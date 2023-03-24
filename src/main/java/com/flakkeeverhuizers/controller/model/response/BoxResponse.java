package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxResponse {

    private String id;
    private String boxId;
    private String code;
    private String name;
    private BigDecimal volume;
    private Boolean fragile;
}
