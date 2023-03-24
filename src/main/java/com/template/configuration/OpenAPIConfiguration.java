package com.template.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@OpenAPIDefinition(
        info = @Info(
                title = "template",
                description = "API of the template application",
                version = "1.0"
        ),
        servers = {
                @Server(url = "http://localhost:8080/", description = "default localhost-8080")
        },
        tags = {
                @Tag(name = "template", description = "template controller")
        }
)
@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenApiCustomiser ordering() {
        // Fix problem of sorting and duplication of tags in output yaml file
        return openApi -> {
            OpenAPIDefinition def = this.getClass().getAnnotation(OpenAPIDefinition.class);
            final List<io.swagger.v3.oas.models.tags.Tag> resultTags = new ArrayList<>();
            if (def != null && def.tags() != null && def.tags().length > 0) {
                resultTags.addAll(
                        Arrays.stream(def.tags())
                                .map(t -> {
                                    var tag = new io.swagger.v3.oas.models.tags.Tag();
                                    tag.setName(t.name());
                                    tag.setDescription(t.description());
                                    return tag;
                                }).collect(Collectors.toList()));
            }
            List<io.swagger.v3.oas.models.tags.Tag> apiTags = openApi.getTags().stream()
                    .filter(tag -> (tag.getDescription() != null && !resultTags.contains(tag)))
                    .collect(Collectors.toList());
            resultTags.addAll(apiTags);
            openApi.setTags(resultTags);
        };
    }
}
