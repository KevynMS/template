package com.flakkeeverhuizers.generator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repo {

    private String name;
    private String fileName;
    private String completeFilePath;
}
