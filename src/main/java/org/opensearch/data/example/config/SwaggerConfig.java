package org.opensearch.data.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("Spring Data OpenSearch example")
                .description("Spring Data OpenSearch example with Testcontainers")
                .version("v0.0.2")
                .contact(getContactDetails()));
    }

    private Contact getContactDetails() {
        return new Contact().name("Mahdi Razavi")
                .email("Razavi.Dev@gmail.com")
                .url("https://m-razavi.github.io");
    }
}