package com.flakkeeverhuizers.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.flakkeeverhuizers.generator.Constants.*;

public class GeneratorV2 {


    public static void main(String[] args) {
        teste();
        // ler sql e popular um objeto de conversao  - casa tabla sera mapeado para um objeto - cada objeto vai ser a lista de atributos e nome da tabela - os objetos seram salvos em uma lista
        // varrer essa lista aqui e realizar os processos para cada class
        List<TableObject> tableObjectList = convertSqlTableToClass(SQL_FILE_PATH);

        // Replace dos nomes pelo nome da aplicacao

        // criar entity
       // createEntity(tableObjectList);

        // criar request dto a partir da entity
        //createRequest(tableObjectList);

        // criar repo
        //createRepo(tableObjectList);

        // criar interface de projection que representa o conteudo de retorno

        // criar service - service unica - geral
        //createService(tableObjectList);

        // criar controller - controller unica - geral
         //createController(tableObjectList);

        // criar exception
        //createException();
    }

    public static void createEntity(List<TableObject> tableObjectList) {
        tableObjectList.forEach(tableObject -> createEntity(tableObject.getEntity(), tableObject.getProjectName()));
    }

    public static void createEntity(Entity entity, String projectName) {
        Path newFilePath = Paths.get(entity.getCompleteFilePath());
        try (BufferedWriter writer =
                     Files.newBufferedWriter(newFilePath, StandardCharsets.UTF_8)) {

            writer.write(getConstant(ENTITY_PACKAGE, projectName) +"\n\n\n");

            writer.write("import lombok.AllArgsConstructor;\n");
            writer.write("import lombok.Data;\n");
            writer.write("import lombok.NoArgsConstructor;\n");
            writer.write("import org.hibernate.annotations.GenericGenerator;\n");
            writer.write("import org.hibernate.annotations.Type;\n\n");


            writer.write("import java.math.BigDecimal;\n");
            writer.write("import java.time.LocalDateTime;\n");
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
            for (Attributes attribute : entity.getAttributes()){
                if (index == 0) {
                    String type = '"' + "uuid-char" + '"';
                    String generator = '"' + "uuid2" + '"';
                    String columIDType = '"' + "char(36)" + '"';

                    writer.write("\t@Id\n");
                    writer.write("\t@GeneratedValue(generator = " + generator + ")\n");
                    writer.write("\t@GenericGenerator(name = " + generator + ", strategy = " + generator + ")\n");
                    writer.write("\t@Column(name = " + attribute.getFieldNameInTable() + ", updatable = false, nullable = false, columnDefinition = " + columIDType + ")\n");
                    writer.write("\t@Type(type = " + type + ")\n");
                    writer.write("\tprivate " +  attribute.getFieldTypeInClass() + " " + attribute.getFieldNameInClass() + ";\n\n");
                } else {
                    if (attribute.getCardinalityType().equals("manyToOne")) {
                        writer.write("\t@ManyToOne(fetch = FetchType.LAZY)\n");
                        writer.write("\t@JoinColumn(name = " + attribute.getFieldNameInTable() + ")\n");
                        writer.write("\tprivate " + attribute.getFieldTypeInClass() + " " + attribute.getFieldNameInClass() + ";\n\n");
                    } else if (attribute.getCardinalityType().equals("manyToMany")) {

                    } else if (attribute.getCardinalityType().equals("oneToOne")) {
                        // TODO pensar numa maneira de pegar o nome do campo na classe que contem a parte manyToOne
                        writer.write("\t@OneToMany(fetch = FetchType.LAZY, mappedBy = <nome_do_campo>>)\n");
                        writer.write("\tprivate Set<"+ attribute.getFieldTypeInClass() +">"+ " " +  attribute.getFieldNameInClass() +";\n");

                    } else {
                        writer.write("\t@Column(name = " + attribute.getFieldNameInTable() + ")\n");
                        writer.write("\tprivate " + attribute.getFieldTypeInClass()  + " " + attribute.getFieldNameInClass() + ";\n\n");
                    }
                }
                index++;
            }
            writer.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createRequest(List<TableObject> tableObjectList) {
        tableObjectList.forEach(tableObject -> createRequest(tableObject.getRequest(), tableObject.getEntity(), tableObject.getProjectName()));
    }

    public static void createRequest(Request request, Entity entity, String projectName) {
        Path originPath = Paths.get(request.getCompleteEntityFilePath());
        Path targetPath = Paths.get(request.getCompleteFilePath());

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
                        writer.write(getConstant(REQUEST_PACKAGE, projectName)+"\n\n");
                        writer.write("import "+ completeClassPath(entity.getName(), getConstant(ENTITY_PACKAGE, projectName)) +";\n");
                    } else if (line.contains("public class")) {
                        writer.write("public class " + request.getName() + " {\n");
                        writer.write("\n\n");

                        writer.write("\tpublic " +  entity.getName() + " convertToEntity(){\n");
                        writer.write("\t\t" +  entity.getName() + " " + firstCharLowerCase(entity.getName()) + " = new " + entity.getName() + "();\n\n");

                        for(Attributes attribute : entity.getAttributes()){
                            if(!attribute.getFieldTypeInClass().equals("UUID")) {
                                // se tiver cardinalidade, usar o method de conversao
                                if(!attribute.getCardinalityType().isEmpty()) {
                                    writer.write("\t\t" + firstCharLowerCase(entity.getName())+ setMethodFromFieldName(attribute.getFieldNameInClass())
                                            .replace("***", "this" + getMethodFromFieldName(attribute.getFieldNameInClass()).replace(";", "") + ".convertToEntity()") + "\n");
                                }else{
                                    writer.write("\t\t" + firstCharLowerCase(entity.getName())+ setMethodFromFieldName(attribute.getFieldNameInClass())
                                            .replace("***", "this" + getMethodFromFieldName(attribute.getFieldNameInClass()).replace(";", "")) + "\n");
                                }
                            }
                        }

                        writer.write("\n");
                        writer.write("\t\treturn " + firstCharLowerCase(entity.getName()) + ";\n");
                        writer.write("\t}\n\n");

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
                                writer.write("\t" + partsOfLine[0] + " " + fromPluralToSingular(partsOfLine[1]) + "Request " + partsOfLine[2] + "\n");
                            } else {
                                if (!line.contains("UUID")) {
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

    public static void createRepo(List<TableObject> tableObjectList){
        tableObjectList.forEach(tableObject -> createRepo(tableObject.getRepo(), tableObject.getEntity(), tableObject.getProjectName()));
    }


    public static void createRepo(Repo repo, Entity entity, String projectName) {
        Path newFilePath = Paths.get(repo.getCompleteFilePath());
        try (BufferedWriter writer =
                     Files.newBufferedWriter(newFilePath, StandardCharsets.UTF_8)) {

            writer.write(getConstant(REPO_PACKAGE, projectName)+"\n\n\n");
            writer.write("import " + completeClassPath("entity.getName()", getConstant(ENTITY_PACKAGE, projectName)) +";\n");
            writer.write("import org.springframework.data.jpa.repository.JpaRepository;\n");
            writer.write("import org.springframework.stereotype.Repository;\n\n");

            writer.write("import java.util.List;\n");
            writer.write("import java.util.UUID;\n\n");

            writer.write("@Repository\n");
            writer.write("public interface " + repo.getName() + " extends JpaRepository<" + entity.getName() + ", UUID> {\n");

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

    public static void createService(List<TableObject> tableObjectList, String projectName){
        Path targetPath = Paths.get(getConstant(SERVICE_PATH, projectName) + "GlobalService" + FILE_TYPE );

        try (BufferedWriter writer = Files.newBufferedWriter(targetPath, StandardCharsets.UTF_8)) {
            writer.write(getConstant(SERVICE_PACKAGE, projectName)+"\n\n\n");

            writer.write("import lombok.RequiredArgsConstructor;\n");
            writer.write("import lombok.extern.slf4j.Slf4j;\n");
            writer.write("import org.springframework.stereotype.Service;\n\n");

            writer.write("import java.util.ArrayList;\n");
            writer.write("import java.util.List;\n");
            writer.write("import " + completeClassPath("*", getConstant(ENTITY_PACKAGE , projectName))+";\n");
            writer.write("import " + completeClassPath("*", getConstant(REPO_PACKAGE, projectName)) +";\n");
            writer.write("import " + completeClassPath("*", getConstant(REQUEST_PACKAGE, projectName)) +";\n");
            writer.write("import " + completeClassPath("*", getConstant(RESPONSE_PACKAGE, projectName)) +";\n");

            writer.write("@RequiredArgsConstructor\n");
            writer.write("@Slf4j\n");
            writer.write("@Service(value = " + '"' + "globalService" + '"'+ ")\n");
            writer.write("public class GlobalService {\n\n");

            // inicio do loop para criar as injecoes de depdendencia
            for(TableObject tableObject : tableObjectList){
                writer.write("\tprivate final " + tableObject.getRepo().getName() + " " + firstCharLowerCase(tableObject.getRepo().getName()) + ";\n\n");
            }

            for(TableObject tableObject : tableObjectList) {
                writer.write("\tpublic void save(" + tableObject.getRequest().getName() + " " + firstCharLowerCase(tableObject.getRequest().getName()/*requestName*/) + ") {\n");
                writer.write("\t\t" + firstCharLowerCase(tableObject.getRepo().getName()/*repoName*/) + ".save(" + firstCharLowerCase(tableObject.getRequest().getName()) + ".convertToEntity());\n");
                writer.write("\t}\n\n");
            }

            writer.write("}");
        }catch (Exception e){
            System.out.println("Error - createService method " + e.getMessage());
        }
    }

    public static void createController(List<TableObject> tableObjectList, String projectName){
        Path targetPath = Paths.get(getConstant(CONTROLLER_PATH, projectName) + "GlobalController" + FILE_TYPE );

        try (BufferedWriter writer = Files.newBufferedWriter(targetPath, StandardCharsets.UTF_8)) {
            writer.write(getConstant(CONTROLLER_PACKAGE, projectName) + "\n\n\n");

            writer.write("import " + completeClassPath("*", getConstant(REQUEST_PACKAGE, projectName))+";\n");
            writer.write("import " + completeClassPath("*", getConstant(RESPONSE_PACKAGE, projectName)) +";\n");
            writer.write("import " + completeClassPath("*", getConstant(SERVICE_PACKAGE, projectName)) +";\n");
            writer.write("import " + completeClassPath("TokenProvider", getConstant(TOKEN_PROVIDER_PACKAGE, projectName)) +";\n");


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
            writer.write("import javax.validation.Valid;\n\n");


            writer.write("@RestController\n");
            writer.write("@Tag(name = " + '"' + "Geral Controller"+ '"' + ")\n");
            writer.write("@RequestMapping(path = " +  '"' + "/" + '"' + ")\n");
            writer.write("@Validated\n");
            writer.write("@RequiredArgsConstructor\n");
            writer.write("@Slf4j\n");
            writer.write("public class GlobalController {\n\n");


            writer.write("\tprivate final GlobalService globalService;\n\n");

            for(TableObject tableObject : tableObjectList){
                writer.write("\t@PostMapping(path = " + '"' + fromPluralToSingular(firstCharLowerCase(tableObject.getEntity().getName())) + '"' + ")\n");
                writer.write("\tpublic void save(@Valid @RequestBody " + tableObject.getRequest().getName() + " " + firstCharLowerCase(tableObject.getRequest().getName()) + "){\n");
                writer.write("\t\tglobalService.save(" + firstCharLowerCase(tableObject.getRequest().getName()) +");\n");

                writer.write("\t}\n\n");
            }

            writer.write("}\n");

        }catch (Exception e){
            System.out.println("Error - createController method " + e.getMessage());
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

    public static String fromPluralToSingular(String plural){
        if(plural.endsWith("es")){
            if (plural.endsWith("ies")) {
                return plural.substring(0, plural.length() - 3) + "y";
            } else if (plural.charAt(plural.length() - 3) == 's' || plural.charAt(plural.length() - 3) == 'h' || plural.charAt(plural.length() - 3) == 'x'){
                return plural.substring(0, plural.length() - 2);
            } else {
                plural.substring(0, plural.length()-1);
            }
        }
        return plural.substring(0, plural.length()-1);
    }

    public static String firstCharUpperCase(String s) {
        return s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1);
    }

    public static String firstCharLowerCase(String s) {
        return s.substring(0, 1).toLowerCase(Locale.ROOT) + s.substring(1);
    }

    public static String completeClassPath(String className, String packagePath){
        return packagePath
                .replace("package", "")
                .replace(";","")
                .trim() + "." + className;
    }

    public static List<TableObject> convertSqlTableToClass(String completeFilePath){
        Path filePath = Paths.get(completeFilePath);
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

                if(line.contains("----")){
                    continue;
                }

                // primeira linha deve conter nome do projeto, nome do db, user, senha e host
                if(line.contains("project")){
                    tableObject.setProjectName(line.replace("project", ""));
                    continue;
                }

                if(line.contains("redis")){
                    continue;
                }

                if(line.contains("@@@@")){
                    String tableName = partsOfLine[0].replace("`", "").replace("@@@@", "").trim();
                    entity.setTableName(tableName);
                    entity.setTableNameWithQuotationMarks( '"' + tableName + '"');

                    String entityName = convertTableFieldToClassField(tableName, true);
                    String entityClass = entityName + FILE_TYPE;
                    entity.setCompleteFilePath(getConstant(ENTITY_PATH, tableObject.getProjectName()) + entityClass);
                    entity.setName(entityName);
                    entity.setFileName(entityClass);

                }else{
                    if(line.contains("****")){
                        // Entity
                        entity.setAttributes(attributes);
                        tableObject.setEntity(entity);

                        // Request
                        String requestName = fromPluralToSingular(entity.getName()) + "Request";
                        String requestClass = requestName + FILE_TYPE;
                        tableObject.setRequest(new Request(requestName, requestClass, getConstant(REQUEST_PATH, tableObject.getProjectName()) + requestClass, entity.getCompleteFilePath()));

                        // Repo
                        String repoName = entity.getName() + "Repository";
                        String repoClass = repoName + FILE_TYPE;
                        tableObject.setRepo(new Repo(repoName, repoClass, getConstant(REPO_PATH , tableObject.getProjectName())+ repoClass));

                        tableObjectList.add(tableObject);

                        // zera os objetos e atribui novas instancias
                        tableObject = new TableObject();
                        entity = new Entity();
                        attributes = new ArrayList<>();
                        position = 0;
                    }else{
                        Attributes attribute = new Attributes();

                        String filedName =  partsOfLine[0].replace("`", "").trim() ;
                        attribute.setFieldNameInTable('"' + filedName + '"');
                        attribute.setFieldNameInClass(convertTableFieldToClassField(filedName, false));
                        attribute.setFieldTypeInClass(convertType(partsOfLine[1], filedName, position));
                        attribute.setCardinalityType(position != 0 && filedName.contains("_id") ? "manyToOne" : "");

                        attributes.add(attribute);
                        position++;
                    }
                }
            }
        }catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        return tableObjectList;
    }


    public static String setMethodFromFieldName(String field){
        return ".set" + firstCharUpperCase(field) + "(***);";
    }

    public static String getMethodFromFieldName(String field){
        return ".get" + firstCharUpperCase(field) + "();";
    }


    public static String convertType(String originalType, String fieldName, int position) {
        //se for a primeira linha do aquivo, e esperado que seja a linha com o id da tabela
        if (position == 0) {
            return "UUID";
        }

        //se nao for a primeiar linha mas tem id, logo e uma linha que contem uma chave estrangeira
        if (position != 0 && fieldName.toLowerCase(Locale.ROOT).contains("id")) {
            return convertTableFieldToClassField(fieldName, true);
        }

        if (originalType.toLowerCase(Locale.ROOT).contains("int")) {
            if (originalType.toLowerCase(Locale.ROOT).contains("tiny")) {
                return "Boolean";
            }
            return "int";
        }

        if (originalType.toLowerCase(Locale.ROOT).contains("varchar") ||
                originalType.toLowerCase(Locale.ROOT).contains("text")) {
            return "String";
        }

        if (originalType.toLowerCase(Locale.ROOT).contains("datetime")) {
            return "LocalDateTime";
        }

        if (originalType.toLowerCase(Locale.ROOT).contains("decimal")) {
            return "BigDecimal";
        }

        if (originalType.toLowerCase(Locale.ROOT).contains("double")) {
            return "double";
        }

        return originalType;
    }

    public static void teste(){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        //Path source = Paths.get(System.getProperty("user.dir"));
        Path source = Paths.get("");

        try {
            System.out.println(Files.isDirectory(source));
            Files.list(source).forEach(path -> {
                System.out.println(path.getFileName());
            });

        }catch (Exception e){

        }

    }

    public static void renameFolder(){
        Path source = Paths.get("src/main/java/com/flakkeeverhuizers/departamento");
        try {
            // verifica se e diretorio
            System.out.println(Files.isDirectory(source));
            // como mudar nome de diretorio
            //Files.move(source, source.resolveSibling("teste"));


            //Files.walkFileTree(source, new FileVisitor());

            Files.list(source).forEach(path -> {
                System.out.println(path.getFileName());

                String line;
                List<String> lines = new ArrayList<>();

                try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
                    while ((line = reader.readLine()) != null) {
                        if(line.isEmpty()){
                            lines.add("\n");
                        }else {
                            lines.add(line);
                        }
                    }
                }catch (Exception e){
                    System.out.println("Error - change file");
                }

                try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
                    for(String lineString : lines){
                        if(lineString.contains("package")){
                            writer.write(lineString.replace("departamento", "teste") + "\n");
                        }else{
                            writer.write(lineString + "\n");
                        }
                    }

                }catch (Exception e){
                    System.out.println("Error - change file");
                }

            });
            Files.move(source, source.resolveSibling("teste"));
        }catch (Exception e){
            System.out.println("Error - renameFolder");
        }
    }
}
