package com.template.generator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectObject {

    private String projectName;
    private String dbName;
    private String user;
    private String pw;
    private String dbHost;
    private String dbType;

    private String tokenTime;
    private String tokenExpTime;
    private String tokenKey;

    private String redisHost;
    private String redisPort;
    private String redisTimeOut;
    private String maxIdle;
    private String minIdle;
    private String maxActive;
    private String maxWait;


    private List<TableObject> tableObjectList;
}
