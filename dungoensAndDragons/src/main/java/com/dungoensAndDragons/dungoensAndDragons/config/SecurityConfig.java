package com.dungoensAndDragons.dungoensAndDragons.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.dungoensAndDragons.dungoensAndDragons.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	return http.build();
    }
//    .csrf(csrf -> csrf.disable())
//	.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/login", "/auth/register").permitAll()
//		.anyRequest().authenticated())
//	.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
//		UsernamePasswordAuthenticationFilter.class);

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	return authConfig.getAuthenticationManager();
    }
}
