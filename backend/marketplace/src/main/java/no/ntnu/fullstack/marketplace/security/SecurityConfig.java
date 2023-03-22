package no.ntnu.fullstack.marketplace.security;

import no.ntnu.fullstack.marketplace.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .requestMatchers("/token").permitAll()//add new fully public endpoints here. H2-console is only for developmen
                .requestMatchers(PathRequest.toH2Console()).permitAll()//add new fully public endpoints here. H2-console is only for developmen
//                .requestMatchers("/user/**").permitAll()//add new fully public endpoints here. fully public only for developmen
                .requestMatchers("/item/**").permitAll()//add new fully public endpoints here. fully public only for developmen
//                .requestMatchers("/h2-console/**").permitAll()//add new fully public endpoints here. only for developmen
//                .requestMatchers("/error/**").permitAll()//add new fully public endpoints here. only for developmen
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .httpBasic().and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .headers().frameOptions().disable()

        ;
        return http.build();
    }
}