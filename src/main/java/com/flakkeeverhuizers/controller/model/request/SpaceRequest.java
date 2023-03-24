package com.flakkeeverhuizers.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceRequest {

    private String name;
    private Boolean custom;
}
