package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiftOptionResponse {

    private String id;
    private String liftId;
    private String name;
    private int sequence;
}
