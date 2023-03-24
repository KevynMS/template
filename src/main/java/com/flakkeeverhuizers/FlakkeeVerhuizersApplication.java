package com.flakkeeverhuizers;

import com.flakkeeverhuizers.configuration.FlakkeeVerhuizersProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FlakkeeVerhuizersProperties.class)
public class FlakkeeVerhuizersApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlakkeeVerhuizersApplication.class, args);
    }

}
