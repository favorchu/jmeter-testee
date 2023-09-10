package com.example.testee;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.BiFunction;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults())
                .csrf((AbstractHttpConfigurer::disable))
                .logout((logout) -> logout.permitAll())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/orders","/orders/**").authenticated()
                        .anyRequest().authenticated()
                )
        ;

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        BiFunction<String, String, UserDetails> buildUser = (username, password)
                -> userBuilder.username(username)
                .password(password)
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(
                buildUser.apply("user01", "password01"),
                buildUser.apply("user02", "password02"),
                buildUser.apply("user03", "password03"),
                buildUser.apply("user04", "password04"),
                buildUser.apply("user05", "password05")
        );
    }
}
