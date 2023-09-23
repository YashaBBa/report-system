package org.bsuir.config;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.csrf(t -> t.disable());
        http.authorizeRequests(auth -> auth
                .antMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/actuator/**").permitAll()
                .regexMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .anyRequest().authenticated()
        );
        http.oauth2ResourceServer(t -> {
            t.jwt(Customizer.withDefaults());
        });
        http.sessionManagement(
                t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }

    @Bean
    public DefaultMethodSecurityExpressionHandler msecurity() {
        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler =
                new DefaultMethodSecurityExpressionHandler();
        defaultMethodSecurityExpressionHandler.setDefaultRolePrefix(""); //указывает, что не нужно использовать префикс "ROLE_" при указании ролей в аннотациях.
        return defaultMethodSecurityExpressionHandler;
    }

    @Bean
    public JwtAuthenticationConverter con() {
        JwtAuthenticationConverter c = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter cv = new JwtGrantedAuthoritiesConverter();
        cv.setAuthorityPrefix(""); // Default "SCOPE_"
        cv.setAuthoritiesClaimName("roles"); // Default "scope" or "scp"
        c.setJwtGrantedAuthoritiesConverter(cv);
        return c;
    }

}
