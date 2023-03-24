package com.flakkeeverhuizers.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiftOptionRequest {

    private String name;
    private int sequence;
}
