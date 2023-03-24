package com.flakkeeverhuizers.generator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attributes {

    private String fieldNameInTable;
    private String fieldNameInClass;
    private String fieldTypeInClass;
    private String cardinalityType;
}
