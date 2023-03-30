package com.template.generator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {

    private String name;
    private String fileName;
    private String completeFilePath;

    private String tableName;

    private String tableNameWithQuotationMarks;

    private List<Attributes> attributes;
}
