package no.ntnu.fullstack.marketplace.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
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