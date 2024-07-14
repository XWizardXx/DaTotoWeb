package org.datotoweb.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfiguration
{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth ->
        {
            auth.anyRequest().authenticated();
        });
        http.oauth2ResourceServer(oauth ->
        {
            oauth.jwt(Customizer.withDefaults());
        });
        http.sessionManagement(sm ->
        {
            sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        return http.build();
    }
}