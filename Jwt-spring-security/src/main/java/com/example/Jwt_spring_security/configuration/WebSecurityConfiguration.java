package com.example.Jwt_spring_security.configuration;


import com.example.Jwt_spring_security.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {

        private final JwtRequestFilter jwtRequestFilter;

        @Autowired
        public WebSecurityConfiguration(JwtRequestFilter jwtRequestFilter) {

                   this.jwtRequestFilter = jwtRequestFilter;
        }


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
                security
                        .csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(authz -> authz
                                .requestMatchers("/signup","/login","/api/**") .permitAll()
                                .anyRequest().authenticated()
                        )
                        .sessionManagement(sess -> sess
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                            .addFilterBefore(jwtRequestFilter , UsernamePasswordAuthenticationFilter.class);

                return security.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder()
        {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

                return configuration.getAuthenticationManager();
        }
}
