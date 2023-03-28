package com.template.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.template.generator.Constants.*;

public class Generator {
    public static void main(String[] args) {
        // ler sql e popular um objeto de conversao  - casa tabla sera mapeado para um objeto - cada objeto vai ser a lista de atributos e nome da tabela - os objetos seram salvos em uma lista
        // varrer essa lista aqui e realizar os processos para cada class
        ProjectObject projectObject = convertConfigFileToProjectObject(CONFIG_FILE);

        //changeProject(projectObject);

        // criar entity
        createEntity(projectObject);

        // criar request dto a partir da entity
        createRequest(projectObject);

        // criar repo
        createRepo(projectObject);

        // criar interface de projection que representa o conteudo de retorno

        // criar service - service unica - geral
        createService(projectObject);

        // criar controller - controller unica - geral
        createController(projectObject);

        // criar exception
        //createException();
    }

    public static void createEntity(ProjectObject projectObject) {
        projectObject.getTableObjectList().forEach(tableObject -> createEntity(tableObject.getEntity(), projectObject.getProjectName()));
    }

    public static void createEntity(Entity entity, String projectName) {
        Path newFilePath = Paths.get(entity.getCompleteFilePath());
        try (BufferedWriter writer =
                     Files.newBufferedWriter(newFilePath, StandardCharsets.UTF_8)) {

            writer.write(getConstant(ENTITY_PACKAGE, projectName) + "\n\n\n");
            writer.write("import lombok.AllArgsConstructor;\n");
            writer.write("import lombok.Data;\n");
            writer.write("import lombok.NoArgsConstructor;\n");
            writer.write("import org.hibernate.annotations.GenericGenerator;\n");
            writer.write("import org.hibernate.annotations.Type;\n\n");

            writer.write("import java.math.BigDecimal;\n");
            writer.write("import java.time.*;\n");
            writer.write("import java.util.Set;\n");
            writer.write("import javax.persistence.*;\n");
            writer.write("import java.util.UUID;\n\n\n");

            writer.write("@Entity\n");
            writer.write("@Table(name = " + entity.getTableNameWithQuotationMarks() + ")\n");
            writer.write("@Data\n");

            writer.write("@AllArgsConstructor\n");
            writer.write("@NoArgsConstructor\n");
            writer.write("public class " + entity.getName() + " {\n\n\n");

            int index = 0;
            for (Attributes attribute : entity.getAttributes()) {
                if (index == 0) {
                    if(attribute.getFieldTypeInClass().equals("UUID")){
                        String type = '"' + "uuid-char" + '"';
                        String generator = '"' + "uuid2" + '"';
                        String columIDType = '"' + "char(36)" + '"';

                        writer.write("\t@Id\n");
                        writer.write("\t@GeneratedValue(generator = " + generator + ")\n");
                        writer.write("\t@GenericGenerator(name = " + generator + ", strategy = " + generator + ")\n");
                        writer.write("\t@Column(name = " + attribute.getFieldNameInTable() + ", updatable = false, nullable = false, columnDefinition = " + columIDType + ")\n");
                        writer.write("\t@Type(type = " + type + ")\n");
                    }else{
                        writer.write("\t@Id\n");
                        writer.write("\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n");
                    }
                    writer.write("\tprivate " + attribute.getFieldTypeInClass() + " " + attribute.getFieldNameInClass() + ";\n\n");
                } else {
                    if (attribute.getCardinalityType().equals("manyToOne")) {
                        writer.write("\t@ManyToOne(fetch = FetchType.LAZY)\n");
                        writer.write("\t@JoinColumn(name = " + attribute.getFieldNameInTable() + ")\n");
                        writer.write("\tprivate " + attribute.getFieldTypeInClass() + " " + attribute.getFieldNameInClass() + ";\n\n");
                    } else if (attribute.getCardinalityType().equals("manyToMany")) {

                    } else if (attribute.getCardinalityType().equals("OneToMany")) {
                        // TODO pensar numa maneira de pegar o nome do campo na classe que contem a parte manyToOne
                        writer.write("\t@OneToMany(fetch = FetchType.LAZY, mappedBy = <nome_do_campo>>)\n");
                        writer.write("\tprivate Set<" + attribute.getFieldTypeInClass() + ">" + " " + attribute.getFieldNameInClass() + ";\n");

                    } else {
                        writer.write("\t@Column(name = " + attribute.getFieldNameInTable() + ")\n");
                        writer.write("\tprivate " + attribute.getFieldTypeInClass() + " " + attribute.getFieldNameInClass() + ";\n\n");
                    }
                }
                index++;
            }
            writer.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createRequest(ProjectObject projectObject) {
        projectObject.getTableObjectList().forEach(tableObject -> createRequest(tableObject, projectObject.getProjectName()));
    }

    public static void createRequest(TableObject tableObject, String projectName) {
        Path originPath = Paths.get(/*request*/ tableObject.getRequest().getCompleteEntityFilePath());
        Path targetPath = Paths.get(/*request*/ tableObject.getRequest().getCompleteFilePath());

        try (BufferedReader reader = Files.newBufferedReader(originPath, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(targetPath, StandardCharsets.UTF_8)) {

            String line;
            List<String> undesiredAnnotations =
                    List.of("@Entity", "@Table", "@Id", "@Gener", "@Column", "@Type", "@Many", "@One", "@Join", "@Lob");

            List<String> nativeJavaTypes =
                    List.of("String", "int", "Integer", "Boolean", "LocalDateTime", "BigDecimal", "double", "UUID");

            while ((line = reader.readLine()) != null) {
                Boolean validLine = true;
                for (String annotation : undesiredAnnotations) {
                    if (line.contains(annotation)) {
                        validLine = false;
                    }
                }
                if (validLine) {
                    if (line.contains("package")) {
                        writer.write(getConstant(REQUEST_PACKAGE, projectName) + "\n\n");
                        writer.write("import " + completeClassPath(/*entity*/ tableObject.getEntity().getName(), getConstant(ENTITY_PACKAGE, projectName)) + ";\n");
                    } else if (line.contains("public class")) {
                        writer.write("public class " + /*request*/tableObject.getRequest().getName() + " {\n");
                        writer.write("\n\n");


                        if (tableObject.getTypeOfFK().equals("object")) {
                            writer.write("\tpublic " + /*entity*/ tableObject.getEntity().getName() + " convertToEntity(){\n");
                            writer.write("\t\t" + /*entity*/ tableObject.getEntity().getName() + " " + firstCharLowerCase(/*entity*/tableObject.getEntity().getName()) + " = new " + /*entity*/ tableObject.getEntity().getName() + "();\n\n");

                            int index = 0;
                            for (Attributes attribute : /*entity*/tableObject.getEntity().getAttributes()) {
                                if (index != 0) {
                                    // se tiver cardinalidade, usar o method de conversao
                                    if (!attribute.getCardinalityType().isEmpty()) {
                                        writer.write("\t\t" + firstCharLowerCase(/*entity*/tableObject.getEntity().getName()) + setMethodFromFieldName(attribute.getFieldNameInClass())
                                                .replace("***", "this" + getMethodFromFieldName(attribute.getFieldNameInClass()).replace(";", "") + ".convertToEntity()") + "\n");
                                    } else {
                                        if (isCreatedOrUpdateDateControlField(attribute.getFieldNameInClass())) {
                                            writer.write("\t\t" + firstCharLowerCase(/*entity*/tableObject.getEntity().getName()) + setMethodFromFieldName(attribute.getFieldNameInClass())
                                                    .replace("***", attribute.getFieldTypeInClass() + ".now()") + "\n");
                                        } else {
                                            writer.write("\t\t" + firstCharLowerCase(/*entity*/tableObject.getEntity().getName()) + setMethodFromFieldName(attribute.getFieldNameInClass())
                                                    .replace("***", "this" + getMethodFromFieldName(attribute.getFieldNameInClass()).replace(";", "")) + "\n");
                                        }
                                    }
                                }
                                index++;
                            }
                            writer.write("\n");
                            writer.write("\t\treturn " + firstCharLowerCase(/*entity*/tableObject.getEntity().getName()) + ";\n");
                            writer.write("\t}\n");
                        }
                    } else {
                        if (!line.equals("") && line.contains("private")) {
                            Boolean isNativeJavaType = false;
                            for (String type : nativeJavaTypes) {
                                if (line.contains(type)) {
                                    isNativeJavaType = true;
                                }
                            }
                            if (!isNativeJavaType) {
                                String[] partsOfLine = line.trim().split(" ");
                                if (tableObject.getTypeOfFK().equals("object")) {
                                    writer.write("\t" + partsOfLine[0] + " " + fromPluralToSingular(partsOfLine[1]) + "Request " + partsOfLine[2] + "\n");
                                } else if(tableObject.getTypeOfFK().equals("just_id")) {
                                    for(Attributes attribute : tableObject.getEntity().getAttributes()){
                                        if(attribute.getFieldTypeInClass().equals(partsOfLine[1])){
                                            writer.write("\t" + partsOfLine[0] + " " +  attribute.getFieldTypeInTable() + " " +  partsOfLine[2] + "\n");
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (!line.contains("UUID") && !line.contains("Integer") && !line.contains("Long") && !isCreatedOrUpdateDateControlField(line)) {
                                    writer.write(line + "\n");
                                }
                            }
                        } else {
                            writer.write(line + "\n");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error - create request " + e.getMessage());
        }
    }

    public static void createRepo(ProjectObject projectObject) {
        projectObject.getTableObjectList().forEach(tableObject -> createRepo(tableObject.getRepo(), tableObject.getEntity(), projectObject.getProjectName()));
    }

    public static void createRepo(Repo repo, Entity entity, String projectName) {
        Path newFilePath = Paths.get(repo.getCompleteFilePath());
        try (BufferedWriter writer =
                     Files.newBufferedWriter(newFilePath, StandardCharsets.UTF_8)) {

            writer.write(getConstant(REPO_PACKAGE, projectName) + "\n\n\n");
            writer.write("import " + completeClassPath(entity.getName(), getConstant(ENTITY_PACKAGE, projectName)) + ";\n");
            writer.write("import org.springframework.data.jpa.repository.JpaRepository;\n");
            writer.write("import org.springframework.stereotype.Repository;\n\n");

            writer.write("import java.util.List;\n");
            writer.write("import java.util.UUID;\n\n");

            writer.write("@Repository\n");
            writer.write("public interface " + repo.getName() + " extends JpaRepository<" + entity.getName() + ", " + entity.getAttributes().get(0).getFieldTypeInClass() +" > {\n");

            writer.write("}");
        } catch (IOException e) {
            System.out.println("Error - create repo " + e.getMessage());
        }
    }

    public static void createException(String completePath, String exceptionName, String httpCode, String projectName) {
        Path newFilePath = Paths.get(completePath);
        try (BufferedWriter writer = Files.newBufferedWriter(newFilePath, StandardCharsets.UTF_8)) {
            writer.write(getConstant(EXCEPTION_PACKAGE, projectName) + "\n\n\n");

            writer.write("public class " + exceptionName + " extends RuntimeException{\n\n");
            writer.write("\tpublic " + exceptionName + " (String message) {\n");
            writer.write("\t\tsuper(message);\n");
            writer.write("\t}\n");
            writer.write("}");

        } catch (Exception e) {
            System.out.println("Error - create exception " + e.getMessage());
        }
    }

    public static void createService(ProjectObject projectObject) {
        Path targetPath = Paths.get(getConstant(SERVICE_PATH, projectObject.getProjectName()) + "GlobalService" + FILE_TYPE);
        try (BufferedWriter writer = Files.newBufferedWriter(targetPath, StandardCharsets.UTF_8)) {
            writer.write(getConstant(SERVICE_PACKAGE, projectObject.getProjectName()) + "\n\n\n");

            writer.write("import lombok.RequiredArgsConstructor;\n");
            writer.write("import lombok.extern.slf4j.Slf4j;\n");
            writer.write("import org.springframework.stereotype.Service;\n\n");

            writer.write("import java.util.ArrayList;\n");
            writer.write("import java.util.List;\n");
            writer.write("import " + completeClassPath("*", getConstant(ENTITY_PACKAGE, projectObject.getProjectName())) + ";\n");
            writer.write("import " + completeClassPath("*", getConstant(REPO_PACKAGE, projectObject.getProjectName())) + ";\n");
            writer.write("import " + completeClassPath("*", getConstant(REQUEST_PACKAGE, projectObject.getProjectName())) + ";\n");
            writer.write("import " + completeClassPath("*", getConstant(EXCEPTION_PACKAGE, projectObject.getProjectName())) + ";\n\n");

            writer.write("@RequiredArgsConstructor\n");
            writer.write("@Slf4j\n");
            writer.write("@Service(value = " + '"' + "globalService" + '"' + ")\n");
            writer.write("public class GlobalService {\n\n");

            // inicio do loop para criar as injecoes de depdendencia
            for (TableObject tableObject : projectObject.getTableObjectList()) {
                writer.write("\tprivate final " + tableObject.getRepo().getName() + " " + firstCharLowerCase(tableObject.getRepo().getName()) + ";\n\n");
            }


            for (TableObject tableObject : projectObject.getTableObjectList()) {
                writer.write("\tpublic void save(" + tableObject.getRequest().getName() + " " + firstCharLowerCase(tableObject.getRequest().getName()/*requestName*/) + ") {\n");
                if (tableObject.getTypeOfFK().equals("object")) {

                    writer.write("\t\t" + firstCharLowerCase(tableObject.getRepo().getName()/*repoName*/) + ".save(" + firstCharLowerCase(tableObject.getRequest().getName()) + ".convertToEntity());\n");

                }else if(tableObject.getTypeOfFK().equals("just_id")) {
                    writer.write("\t\t" + tableObject.getEntity().getName() /*entityName*/ + " " + firstCharLowerCase(/*entityName*/ tableObject.getEntity().getName()) + " = new " + /*entityName*/ tableObject.getEntity().getName() + "();\n");

                    int index = 0;
                    for(Attributes attribute : tableObject.getEntity().getAttributes()){
                        // index 0 significa que e a key, e a key so e gerada apos a criacao do registro
                        if (index != 0) {
                            if (!attribute.getCardinalityType().isEmpty()) {


                                String recordNotFound =  '"' + attribute.getFieldNameInClass() + " not found" + '"';
                                writer.write("\t\t" + firstCharLowerCase(tableObject.getEntity().getName()/*entityName*/) + setMethodFromFieldName(attribute.getFieldNameInClass())
                                        //                    (first char minusculo) fieldName + ".findById(" +  request.getFieldName + ")" +   ".orElseThrow(() -> new RecordNotFoundException(error message)));"
                                        .replace("***",
                                                firstCharLowerCase(attribute.getFieldNameInClass()) + "Repository.findById(" +
                                                firstCharLowerCase(tableObject.getRequest().getName()) + getMethodFromFieldName(attribute.getFieldNameInClass()).replace(";", ""))
                                        .replace(";", "") + ".orElseThrow(() -> new RecordNotFoundException(" + recordNotFound + ")));" + "\n");
                            } else {
                                writer.write("\t\t" + firstCharLowerCase(tableObject.getEntity().getName()/*entityName*/) + setMethodFromFieldName(attribute.getFieldNameInClass())
                                        .replace("***", firstCharLowerCase(tableObject.getRequest().getName()/*requestName*/) + getMethodFromFieldName(attribute.getFieldNameInClass()).replace(";", "")) + "\n");
                            }
                        }
                        index++;
                    }

                    writer.write("\t\t"+ firstCharLowerCase(tableObject.getRepo().getName()/*repoName*/) + ".save(" + firstCharLowerCase(tableObject.getEntity().getName()/*entityName*/) + ");\n");
                }
                writer.write("\t}\n\n");
            }
            writer.write("}");
        } catch (Exception e) {
            System.out.println("Error - createService method " + e.getMessage());
        }
    }

    public static void createController(ProjectObject projectObject) {
        Path targetPath = Paths.get(getConstant(CONTROLLER_PATH, projectObject.getProjectName()) + "GlobalController" + FILE_TYPE);
        try (BufferedWriter writer = Files.newBufferedWriter(targetPath, StandardCharsets.UTF_8)) {
            writer.write(getConstant(CONTROLLER_PACKAGE, projectObject.getProjectName()) + "\n\n\n");

            writer.write("import " + completeClassPath("*", getConstant(ENTITY_PACKAGE, projectObject.getProjectName())) + ";\n");
            writer.write("import " + completeClassPath("*", getConstant(REQUEST_PACKAGE, projectObject.getProjectName())) + ";\n");
            writer.write("import " + completeClassPath("*", getConstant(SERVICE_PACKAGE, projectObject.getProjectName())) + ";\n");
            writer.write("import " + completeClassPath("TokenProvider", getConstant(TOKEN_PROVIDER_PACKAGE, projectObject.getProjectName())) + ";\n");

            writer.write("import org.springframework.http.ResponseEntity;\n");
            writer.write("import org.springframework.security.authentication.AuthenticationManager;\n");
            writer.write("import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;\n");
            writer.write("import org.springframework.security.core.Authentication;\n");
            writer.write("import org.springframework.security.core.context.SecurityContextHolder;\n");
            writer.write("import org.springframework.web.bind.annotation.*;\n");
            writer.write("import org.springframework.web.multipart.MultipartFile;\n");

            writer.write("import io.swagger.v3.oas.annotations.tags.Tag;\n");
            writer.write("import lombok.RequiredArgsConstructor;\n");
            writer.write("import lombok.extern.slf4j.Slf4j;\n");
            writer.write("import org.springframework.validation.annotation.Validated;\n");
            writer.write("import java.util.HashMap;\n");
            writer.write("import java.util.Map;\n");
            writer.write("import javax.validation.Valid;\n\n");

            writer.write("@RestController\n");
            writer.write("@Tag(name = " + '"' + "Geral Controller" + '"' + ")\n");
            writer.write("@RequestMapping(path = " + '"' + "/" + '"' + ")\n");
            writer.write("@Validated\n");
            writer.write("@RequiredArgsConstructor\n");
            writer.write("@Slf4j\n");
            writer.write("public class GlobalController {\n\n");

            writer.write("\tprivate final UserService userService;\n");
            writer.write("\tprivate final AuthenticationManager authenticationManager;\n");
            writer.write("\tprivate final TokenProvider jwtTokenUtil;\n");
            writer.write("\tprivate final GlobalService globalService;\n\n");

            writer.write("\t@PostMapping(path = " + '"' + "login" + '"' + ")\n");
            writer.write("\tpublic ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) {\n");

            writer.write("\t\tfinal Authentication authentication = authenticationManager.authenticate(\n");
            writer.write("\t\t\tnew UsernamePasswordAuthenticationToken(\n");
            writer.write("\t\t\t\tloginRequest.getEmail(),\n");
            writer.write("\t\t\t\tloginRequest.getPassword()\n");
            writer.write("\t\t\t)\n");
            writer.write("\t\t);\n");
            writer.write("\t\tSecurityContextHolder.getContext().setAuthentication(authentication);\n");
            writer.write("\t\tfinal String token = jwtTokenUtil.generateToken(authentication, null);\n");
            writer.write("\t\treturn ResponseEntity.ok(successLoginResponse(loginRequest.getEmail(), token));\n");
            writer.write("\t}\n\n");

            writer.write("\tprivate Map<String, Object> successLoginResponse(String email, String token) {\n");
            writer.write("\t\tUser user = userService.findByEmail(email);\n");

            writer.write("\t\t\tMap<String, Object> response = new HashMap<>();\n");
            writer.write("\t\t\tresponse.put(" + '"' + "userID" + '"' + ", user.getId().toString());\n");
            writer.write("\t\t\tresponse.put(" + '"' + "token" + '"' + ", token);\n");

            writer.write("\t\treturn response;\n");
            writer.write("\t}\n\n");

            for (TableObject tableObject : projectObject.getTableObjectList()) {
                writer.write("\t@PostMapping(path = " + '"' + fromPluralToSingular(firstCharLowerCase(tableObject.getEntity().getName())) + '"' + ")\n");
                writer.write("\tpublic void save(@Valid @RequestBody " + tableObject.getRequest().getName() + " " + firstCharLowerCase(tableObject.getRequest().getName()) + "){\n");
                writer.write("\t\tglobalService.save(" + firstCharLowerCase(tableObject.getRequest().getName()) + ");\n");
                writer.write("\t}\n\n");
            }
            writer.write("}\n");
        } catch (Exception e) {
            System.out.println("Error - createController method " + e.getMessage());
        }
    }


    public static void changeProject(ProjectObject projectObject) {
        Path rootPath = Paths.get("src/main/java/com/template");

        changeFiles(projectObject, Paths.get("Dockerfile.api"));
        changeFiles(projectObject, Paths.get("README.md"));
        changeFiles(projectObject, Paths.get("settings.gradle"));
        changeProperties(projectObject, Paths.get(RESOURCE + "application.properties"));
        changeProperties(projectObject, Paths.get(RESOURCE + "application-dev.properties"));
        renamePackage(projectObject, rootPath);
        renameProject(projectObject, rootPath);
    }

    public static void changeProperties(ProjectObject projectObject, Path source) {
        List<String> lines = fromFileToList(source);
        try (BufferedWriter writer = Files.newBufferedWriter(source, StandardCharsets.UTF_8)) {
            for (String lineString : lines) {
                writer.write(
                        lineString
                                .replace(DEFAULT_PROJECT_NAME, projectObject.getProjectName())
                                .replace("DBHOST", projectObject.getDbHost())
                                .replace("DBUSER", projectObject.getDbUser())
                                .replace("DBPW", projectObject.getDbPw())
                                .replace("DBNAME", projectObject.getDbName())
                                .replace("DBTYPE", projectObject.getDbType())
                                .replace("DBPORT", projectObject.getDbPort())
                                .replace("DBDRIVER", projectObject.getDbDriverClass())
                                .replace("DBPLATFORM", projectObject.getDbPlatform())
                                .replace("REDISHOST", projectObject.getRedisHost())
                                .replace("REDISPORT", projectObject.getRedisPort())
                                .replace("REDISTIMEOUT", projectObject.getRedisTimeOut())
                                .replace("REDISMAXIDLE", projectObject.getMaxIdle())
                                .replace("REDISMINIDLE", projectObject.getMinIdle())
                                .replace("REDISMAXACTIVE", projectObject.getMaxActive())
                                .replace("REDISMAXWAIT", projectObject.getMaxWait())
                                .replace("TOKENTIME" , projectObject.getTokenTime())
                                .replace("TOKENREFRESHTIME", projectObject.getTokenRefreshTime())
                                .replace("TOKENKEY", projectObject.getTokenKey())
                                + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error - properties");
        }
    }

    public static void changeFiles(ProjectObject projectObject, Path source) {
        List<String> lines = fromFileToList(source);

        try (BufferedWriter writer = Files.newBufferedWriter(source, StandardCharsets.UTF_8)) {
            for (String lineString : lines) {
                writer.write(lineString.replace(DEFAULT_PROJECT_NAME, projectObject.getProjectName()) + "\n");
            }

        } catch (Exception e) {
            System.out.println("Error - dockerfile");
        }
    }

    public static void renameProject(ProjectObject projectObject, Path source) {
        try {
            Files.move(source, source.resolveSibling(projectObject.getProjectName()));
        } catch (Exception e) {

            System.out.println("Error - rename project");
        }
    }

    public static void renamePackage(ProjectObject projectObject, Path source) {
        try {
            System.out.println(Files.isDirectory(source));
            Files.walk(source).forEach(path -> {
                if (!Files.isDirectory(path)) {
                    String line;
                    List<String> lines = new ArrayList<>();
                    try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                        while ((line = reader.readLine()) != null) {
                            if (line.isEmpty()) {
                                lines.add("\n");
                            } else {
                                lines.add(line);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error - change file");
                    }
                    try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                        for (String lineString : lines) {
                            writer.write(lineString.replace(DEFAULT_PROJECT_NAME, projectObject.getProjectName()) + "\n");
                        }
                    } catch (Exception e) {
                        System.out.println("Error - change file");
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Error - renameFolder");
        }
    }

    /*************************** metodos auxiliares *******************/

    public static String convertTableFieldToClassField(String fieldName, Boolean toClassName) {
        StringBuilder sb = new StringBuilder(fieldName.replace("_id", ""));
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '_') {
                sb.deleteCharAt(i);
                sb.replace(i, i + 1, String.valueOf(Character.toUpperCase(sb.charAt(i))));
            }
        }
        if (toClassName)
            return firstCharUpperCase(sb.toString());

        return sb.toString();
    }

    public static String fromPluralToSingular(String plural) {
        if (plural.endsWith("es")) {
            if (plural.endsWith("ies")) {
                return plural.substring(0, plural.length() - 3) + "y";
            } else if (plural.charAt(plural.length() - 3) == 's' || plural.charAt(plural.length() - 3) == 'h' || plural.charAt(plural.length() - 3) == 'x') {
                return plural.substring(0, plural.length() - 2);
            } else {
                plural.substring(0, plural.length() - 1);
            }
        }
        return plural.endsWith("s") ? plural.substring(0, plural.length() - 1) : plural;
    }

    public static String firstCharUpperCase(String s) {
        return s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1);
    }

    public static String firstCharLowerCase(String s) {
        return s.substring(0, 1).toLowerCase(Locale.ROOT) + s.substring(1);
    }

    public static String completeClassPath(String className, String packagePath) {
        return packagePath
                .replace("package", "")
                .replace(";", "")
                .trim() + "." + className;
    }

    public static ProjectObject convertConfigFileToProjectObject(String completeFilePath) {
        Path filePath = Paths.get(completeFilePath);
        ProjectObject projectObject = new ProjectObject();
        List<TableObject> tableObjectList = new ArrayList<>();
        try (BufferedReader reader =
                     Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            int position = 0;

            TableObject tableObject = new TableObject();
            Entity entity = new Entity();
            List<Attributes> attributes = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] partsOfLine = line.split(" ");
                if (line.startsWith("----")) {
                    continue;
                }
                // primeira linha deve conter nome do projeto, nome do db, user, senha e host
                if (line.startsWith("project")) {
                    String projectValues[] = line.replace("project", "").split(",");

                    projectObject.setProjectName(projectValues[0].trim());
                    projectObject.setDbName(projectValues[1].trim());
                    projectObject.setDbUser(projectValues[2].trim());
                    projectObject.setDbPw(projectValues[3].trim());
                    projectObject.setDbHost(projectValues[4].trim());
                    projectObject.setDbType(projectValues[5].trim());
                    projectObject.setDbPort(projectValues[6].trim());

                    // todo create e enum for that
                    if (projectObject.getDbType().equals("mysql")) {
                        projectObject.setDbDriverClass("com.mysql.cj.jdbc.Driver");
                        projectObject.setDbPlatform("org.hibernate.dialect.MySQL5InnoDBDialect");

                    } else if (projectObject.getDbType().equals("postgresql")) {
                        projectObject.setDbDriverClass("org.postgresql.Driver");
                        projectObject.setDbPlatform("org.hibernate.dialect.PostgreSQLDialect");
                    }
                    continue;
                }
                if (line.startsWith("token")) {
                    String projectValues[] = line.replace("token", "").split(",");

                    projectObject.setTokenTime(projectValues[0].trim());
                    projectObject.setTokenRefreshTime(projectValues[1].trim());
                    projectObject.setTokenKey(projectValues[2].trim());
                    continue;
                }
                if (line.startsWith("redis")) {
                    String projectValues[] = line.replace("redis", "").split(",");

                    projectObject.setRedisHost(projectValues[0].trim());
                    projectObject.setRedisPort(projectValues[1].trim());
                    projectObject.setRedisTimeOut(projectValues[2].trim());
                    projectObject.setMaxIdle(projectValues[3].trim());
                    projectObject.setMinIdle(projectValues[4].trim());
                    projectObject.setMaxActive(projectValues[5].trim());
                    projectObject.setMaxWait(projectValues[6].trim());
                    continue;
                }
                if (line.startsWith("@@@@")) {
                    String tableValues[] = line.replace("@@@@", "").split(",");
                    entity.setTableName(tableValues[0].trim());
                    tableObject.setTypeOfPK(tableValues[1].trim());
                    tableObject.setTypeOfFK(tableValues[2].trim());
                    entity.setTableNameWithQuotationMarks('"' + entity.getTableName() + '"');

                    String entityName = fromPluralToSingular(convertTableFieldToClassField(tableValues[0].trim(), true));
                    String entityClass = entityName + FILE_TYPE;
                    entity.setCompleteFilePath(getConstant(ENTITY_PATH, projectObject.getProjectName()) + entityClass);
                    entity.setName(entityName);
                    entity.setFileName(entityClass);
                } else {
                    if (line.startsWith("****")) {
                        // Entity
                        entity.setAttributes(attributes);
                        tableObject.setEntity(entity);

                        // Request
                        String requestName = fromPluralToSingular(entity.getName()) + "Request";
                        String requestClass = requestName + FILE_TYPE;
                        tableObject.setRequest(new Request(requestName, requestClass, getConstant(REQUEST_PATH, projectObject.getProjectName()) + requestClass, entity.getCompleteFilePath()));

                        // Repo
                        String repoName = entity.getName() + "Repository";
                        String repoClass = repoName + FILE_TYPE;
                        tableObject.setRepo(new Repo(repoName, repoClass, getConstant(REPO_PATH, projectObject.getProjectName()) + repoClass));

                        tableObjectList.add(tableObject);

                        // zera os objetos e atribui novas instancias
                        tableObject = new TableObject();
                        entity = new Entity();
                        attributes = new ArrayList<>();
                        position = 0;
                    } else {
                        Attributes attribute = new Attributes();
                        String fieldName = partsOfLine[0].replace("`", "").replace("\"", "").trim();
                        attribute.setFieldNameInTable('"' + fieldName + '"');
                        attribute.setFieldNameInClass(convertTableFieldToClassField(fieldName, false));
                        attribute.setFieldTypeInTable(convertType(partsOfLine[1], fieldName.replace("_id", ""), position));
                        attribute.setFieldTypeInClass(convertType(partsOfLine[1], fieldName, position));
                        attribute.setCardinalityType(position != 0 && fieldName.contains("_id") ? "manyToOne" : "");

                        // se posicao 0

                        attributes.add(attribute);
                        position++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        projectObject.setTableObjectList(tableObjectList);
        return projectObject;
    }

    public static String setMethodFromFieldName(String field) {
        return ".set" + firstCharUpperCase(field) + "(***);";
    }

    public static String getMethodFromFieldName(String field) {
        return ".get" + firstCharUpperCase(field) + "();";
    }

    public static String convertType(String originalType, String fieldName, int position) {
        //se nao for a primeiar linha mas tem id, logo e uma linha que contem uma chave estrangeira
        if (position != 0 && fieldName.toLowerCase(Locale.ROOT).contains("_id")) {
            return convertTableFieldToClassField(fieldName, true);
        }

        List<String> convertToBoolean      = List.of("bit", "boolean");
        List<String> convertToString       = List.of("varchar", "char", "longvarchar", "character", "text", "longtext");
        List<String> convertToInteger      = List.of("int", "integer", "serial", "smallint");
        List<String> convertToLong         = List.of("bigint");
        List<String> convertToDouble       = List.of("double");
        List<String> convertToBigDecimal   = List.of("double", "decimal", "money", "numeric");
        List<String> convertToLocalDate    = List.of("date");
        List<String> convertToLocalTime    = List.of("time");
        List<String> convertToLocalDateTme = List.of("timestamp", "datetime");

        for(String elem : convertToBoolean){
            if(originalType.toLowerCase(Locale.ROOT).equals(elem))
                return "Boolean";
        }

        for(String elem : convertToString){
            if(originalType.toLowerCase(Locale.ROOT).equals(elem))
                return "String";
        }

        for(String elem : convertToInteger){
            if(originalType.toLowerCase(Locale.ROOT).equals(elem))
                return "Integer";
        }

        for(String elem : convertToLong){
            if(originalType.toLowerCase(Locale.ROOT).equals(elem))
                return "Long";
        }

        for(String elem : convertToDouble){
            if(originalType.toLowerCase(Locale.ROOT).equals(elem))
                return "Double";
        }

        for(String elem : convertToBigDecimal){
            if(originalType.toLowerCase(Locale.ROOT).equals(elem))
                return "BigDecimal";
        }

        for(String elem : convertToLocalDate){
            if(originalType.toLowerCase(Locale.ROOT).equals(elem))
                return "LocalDate";
        }

        for(String elem : convertToLocalTime){
            if(originalType.toLowerCase(Locale.ROOT).equals(elem))
                return "LocalTime";
        }

        for(String elem : convertToLocalDateTme){
            if(originalType.toLowerCase(Locale.ROOT).equals(elem))
                return "LocalDateTime";
        }

        return originalType;
    }

    public static List<String> fromFileToList(Path source) {
        String line;
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(source, StandardCharsets.UTF_8)) {
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    lines.add("\n");
                } else {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Error - change file");
        }
        return lines;
    }

    public static Boolean isCreatedOrUpdateDateControlField(String line){
        List<String> dateTypes =
                List.of("createdat", "updatedat", "createddt", "updateddt");
        for(String type : dateTypes){
            if(line.toLowerCase().contains(type))
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
