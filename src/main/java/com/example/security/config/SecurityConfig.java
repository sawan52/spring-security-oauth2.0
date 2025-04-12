package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public static final String[] ADMIN_SECURED_API = {"/api/v1/secure1"};
    public static final String[] USER_SECURED_API = {"/api/v1/secure2"};
    public static final String[] USER_ADMIN_SECURED_API = {"/api/v1/secure3"};

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        httpSecurity
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers(ADMIN_SECURED_API).hasRole("ADMIN")
                                .requestMatchers(USER_SECURED_API).hasRole("USER")
                                .requestMatchers(USER_ADMIN_SECURED_API).hasAnyRole("USER", "ADMIN")
                                .anyRequest().permitAll());
        httpSecurity.oauth2ResourceServer(rsc ->
                rsc.jwt(jwtConfigurer ->
                        jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)));
//                .formLogin(Customizer.withDefaults());
//                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }

    /**
     * Below are the implementation of OAuth2.0 with Social Logins
     @Bean ClientRegistrationRepository clientRegistrationRepository() {
     ClientRegistration github = githubClientRegistration();
     ClientRegistration facebook = facebookClientRegistration();
     return new InMemoryClientRegistrationRepository(github, facebook);
     }

     private ClientRegistration githubClientRegistration() {
     return CommonOAuth2Provider.GITHUB.getBuilder("github")
     .clientId("Ov23lisrMeXmxWIcV82b").clientSecret("27523fa5bb0f6596e979934dce04c3b9d8c6dbe4").build();
     }

     private ClientRegistration facebookClientRegistration() {
     return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
     .clientId("683587580808724").clientSecret("69637a857c2793ccc9cfb10911b381df").build();
     }
     */
}
