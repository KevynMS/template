package com.flakkeeverhuizers.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "spoxx.flakkeeverhuizers")
@Data
@Validated
public class FlakkeeVerhuizersProperties {

    /**
     * Default description which is added to new samples
     */
    @NotNull
    @NotBlank
    private String defaultDescription;
}
