package com.api.ejercicio234.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.ejercicio234.config.filters.JwtAuthenticationFilter;
import com.api.ejercicio234.config.filters.JwtAuthorizationFilter;
import com.api.ejercicio234.config.jwt.JwtUtils;
import com.api.ejercicio234.services.impl.UserDetailsServiceImp;

@Configuration
// @EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImp userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager)
            throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        return httpSecurity
                .cors(config -> config.disable())
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll();
                    requests.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
            throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    // @Bean
    // UserDetailsService userDetailsService() {
    // return username -> (UserDetails) userService.findByNick(username)
    // .orElseThrow(() -> new UsernameNotFoundException("Usuario no existe!"));
    // }

    // public static void main(String[] args) {
    // System.out.println(new BCryptPasswordEncoder().encode("1234"));
    // }

}
