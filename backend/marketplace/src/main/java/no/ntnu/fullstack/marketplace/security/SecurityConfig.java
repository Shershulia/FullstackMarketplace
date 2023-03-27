package no.ntnu.fullstack.marketplace.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration for the application using Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the authentication manager builder with the user details service and password encoder.
     * Exposes some endpoints as public. like user registration and login, and item listing. (they might be validated in the controller)
     * H2-console is only for development and should be disabled in prod by commenting out the line in the filterChain method.
     * @param http the http security object
     * @return the security filter chain
     * @throws Exception if something goes wrong
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .requestMatchers( "/user/**").permitAll()//add new fully public endpoints here. H2-console is only for developmen
                .requestMatchers( "/user/**").permitAll()//add new fully public endpoints here. H2-console is only for developmen
                .requestMatchers( "/item/**").permitAll()//add new fully public endpoints here. H2-console is only for developmen
                .requestMatchers("/token").permitAll()//add new fully public endpoints here. H2-console is only for developmen
                .requestMatchers(PathRequest.toH2Console()).permitAll()//add new fully public endpoints here. H2-console is only for developmen
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .headers().frameOptions().disable()

        ;
        return http.build();
    }
}