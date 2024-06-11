package com.ridolphiconsutoria.timesheetv1.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigV2 {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return  http
        .csrf(csrf -> csrf.disable())
         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)) // SessionManagement: Alterei SessionCreationPolicy.STATELESS para SessionCreationPolicy.IF_REQUIRED para permitir cookies de sessão necessários para formLogin.
         .authorizeHttpRequests(authorize -> authorize
         .requestMatchers(HttpMethod.GET,"/home").hasAnyRole("GERENTE","COORDENADOR","CONSULTOR")
         .requestMatchers(HttpMethod.GET,"/login/**").permitAll()
         .anyRequest().authenticated())
        //  .formLogin(formLogin -> formLogin.defaultSuccessUrl("/home").failureUrl("/login?erro=yes").usernameParameter("username").passwordParameter("password")) //login padrão
        .formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/home").failureUrl("/login?erro=yes").usernameParameter("username").passwordParameter("password")) //login personalizado

        .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
