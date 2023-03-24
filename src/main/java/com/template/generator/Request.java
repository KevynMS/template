package com.template.generator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private String name;
    private String fileName;
    private String completeFilePath;
    private String completeEntityFilePath;

}
