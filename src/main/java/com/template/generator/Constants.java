package com.template.generator;


public class Constants {

    private static String DEFAULT_PROJECT_NAME = "flakkeeverhuizers"; // Mudar para template depois

    public static String SQL_FILE_PATH = "/Users/kevynmiranda/Documents/sql.sql";

    public static String ENTITY_PATH = "src/main/java/com/flakkeeverhuizers/db/entity/";
    public static String REPO_PATH = "src/main/java/com/flakkeeverhuizers/db/repository/";
    public static String RESPONSE_PATH = "src/main/java/com/flakkeeverhuizers/controller/model/response/";
    public static String REQUEST_PATH = "src/main/java/com/flakkeeverhuizers/controller/model/request/";
    public static String EXCEPTION_PATH = "src/main/java/com/flakkeeverhuizers/exception/";
    public static String SERVICE_PATH = "src/main/java/com/flakkeeverhuizers/service/";
    public static String CONTROLLER_PATH = "src/main/java/com/flakkeeverhuizers/controller/";

    public static String TOKEN_PROVIDER_PATH = "src/main/java/com/flakkeeverhuizers/configuration/";

    public static String ENTITY_PACKAGE = "package com.flakkeeverhuizers.db.entity;";
    public static String REPO_PACKAGE = "package com.flakkeeverhuizers.db.repository;";
    public static String RESPONSE_PACKAGE = "package com.flakkeeverhuizers.controller.model.response;";
    public static String EXCEPTION_PACKAGE = "package com.flakkeeverhuizers.exception;";
    public static String SERVICE_PACKAGE = "package com.flakkeeverhuizers.service;";
    public static String REQUEST_PACKAGE = "package com.flakkeeverhuizers.controller.model.request;";
    public static String CONTROLLER_PACKAGE = "package com.flakkeeverhuizers.controller;";

    public static String TOKEN_PROVIDER_PACKAGE = "package com.flakkeeverhuizers.configuration;";

    public static String FILE_TYPE = ".java";


    public static String getConstant(String constant, String projectName){
        if(projectName != null){
            return constant.replace(DEFAULT_PROJECT_NAME, projectName);
        }
        return constant;
    }
}
