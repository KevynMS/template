package com.template.generator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableObject {

    private Entity entity;
    private Repo repo;
    private Request request;


}
