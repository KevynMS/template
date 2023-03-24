package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryResponse {

    private String id;
    private String itemCategoryId;
    private String name;
    private String icon;
    private Boolean custom;


}
