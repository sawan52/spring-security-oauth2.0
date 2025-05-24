package com.example.security.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String AUTHORIZATION_URL = "http://localhost:7171/oauth2/authorize";
    private static final String TOKEN_URL = "http://localhost:7171/oauth2/token";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl(AUTHORIZATION_URL)
                                                .tokenUrl(TOKEN_URL)
                                                .scopes(new Scopes().addString("openid", "OpenID Connect Scope"))
                                        )
                                )
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("oauth2"));
    }
}
