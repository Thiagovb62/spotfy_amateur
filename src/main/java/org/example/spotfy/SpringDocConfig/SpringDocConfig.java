package org.example.spotfy.SpringDocConfig;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class SpringDocConfig {
        @Bean
        public OpenAPI openAPI() {
            return new OpenAPI()
                    .components(new Components()
                            .addSecuritySchemes("bearer-key",
                                    new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                    .info(new Info()
                            .title("Test API")
                            .version("v1")
                            .description("REST API Test")
                            .license(new License()
                                    .name("Apache 2.0")
                                    .url("https://springdoc.org")
                            )
                    )
                    .externalDocs(new ExternalDocumentation()
                            .description("Link Test")
                            .url("https://test.com"))
                    .tags(
                            Arrays.asList(
                                    new Tag().name("Admin").description("Admin Messages"),
                                    new Tag().name("PlayList").description("PlayList Messages"),
                                    new Tag().name("Music").description("Music Messages"),
                                    new Tag().name("User").description("User Messages")

                            )
                    );
        }
}
