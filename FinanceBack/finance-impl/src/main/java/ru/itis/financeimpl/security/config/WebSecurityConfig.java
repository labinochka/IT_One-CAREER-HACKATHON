package ru.itis.financeimpl.security.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.financeimpl.security.filter.JwtAuthenticationFilter;
import ru.itis.financeimpl.security.service.JwtService;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {
    private static final String USERNAME_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String LOGIN_PAGE = "/login";
    private static final String LOGIN_PROCESSING_URL = "/sign-in";
    private static final String[] PERMIT_ALL = { LOGIN_PROCESSING_URL, LOGIN_PAGE };
    private static final String[] AUTHENTICATED = { "/api/**" };
    private static final String[] IGNORE = { };

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(manager -> manager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(PERMIT_ALL).permitAll()
                        .requestMatchers(AUTHENTICATED).authenticated()
                )
//                .formLogin(formLogin -> formLogin
//                    .usernameParameter(USERNAME_PARAMETER)
//                    .passwordParameter(PASSWORD_PARAMETER)
//                )
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Filter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }
}
