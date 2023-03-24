package com.template.generator;


public class Constants {

    public static String DEFAULT_PROJECT_NAME = "template"; //

    public static String CONFIG_FILE = "src/main/java/com/template/generator/config_project.txt";

    public static String ENTITY_PATH = "src/main/java/com/template/db/entity/";
    public static String REPO_PATH = "src/main/java/com/template/db/repository/";
    public static String RESPONSE_PATH = "src/main/java/com/template/controller/model/response/";
    public static String REQUEST_PATH = "src/main/java/com/template/controller/model/request/";
    public static String EXCEPTION_PATH = "src/main/java/com/template/exception/";
    public static String SERVICE_PATH = "src/main/java/com/template/service/";
    public static String CONTROLLER_PATH = "src/main/java/com/template/controller/";

    public static String TOKEN_PROVIDER_PATH = "src/main/java/com/template/configuration/";

    public static String RESOURCE = "src/main/resources/";

    public static String ENTITY_PACKAGE = "package com.template.db.entity;";
    public static String REPO_PACKAGE = "package com.template.db.repository;";
    public static String RESPONSE_PACKAGE = "package com.template.controller.model.response;";
    public static String EXCEPTION_PACKAGE = "package com.template.exception;";
    public static String SERVICE_PACKAGE = "package com.template.service;";
    public static String REQUEST_PACKAGE = "package com.template.controller.model.request;";
    public static String CONTROLLER_PACKAGE = "package com.template.controller;";

    public static String TOKEN_PROVIDER_PACKAGE = "package com.template.configuration;";

    public static String FILE_TYPE = ".java";


    public static String getConstant(String constant, String projectName){
        if(projectName != null){
            return constant.replace(DEFAULT_PROJECT_NAME, projectName);
        }
        return constant;
    }
}
