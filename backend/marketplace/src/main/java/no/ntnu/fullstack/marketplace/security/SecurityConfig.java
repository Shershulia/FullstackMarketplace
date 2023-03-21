package no.ntnu.fullstack.marketplace.security;


//was a bit strict with the h2 console, so disabled temporarily

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    // inject SecurityFilterChain and tell that all requests are authenticated
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(
//                                RequestMatcher -> "/h2-console/**".equals(RequestMatcher.getRequestURI())
//                        )
//                )
                .authorizeHttpRequests()
                    .requestMatchers("/token").permitAll()
//                    .requestMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
