package com.mla.socialchristian.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    private Contact contact(){
        return new Contact()
                .name("Social mídia christian")
                .email("mateuslaurentino@gmail.com");
    }

    private Info apiInformation(){
        return new Info()
                .title("Christian social network")
                .description("A social network made for Christian topics and sharing the living word")
                .version("1.0")
                .contact(this.contact());
    }

    private SecurityScheme apiSecurityScheme(){
        return new SecurityScheme()
            .name(SECURITY_SCHEME_NAME)
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT");
    }

    private Components addAuthButton(){
        return new Components()
            .addSecuritySchemes(SECURITY_SCHEME_NAME, this.apiSecurityScheme());
    }

    @Bean
    public OpenAPI apiDetail(){
        return new OpenAPI()
                .info(this.apiInformation())
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(this.addAuthButton())
                .addServersItem(new Server().url("http://localhost:8080/"));
    }
}
