package com.diario.demo.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*Configurazione per definire la sicurezza di accesso agli endpoint*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    
    /*Metodo per configurare la sicurezza*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/home", "/login", "/signin").permitAll() 
                .requestMatchers("/admin/**", "/logout").hasRole("ADMIN")  
                .requestMatchers("/user/**", "logout").hasRole("USER")  
                .requestMatchers("/home", "/login", "/signin").permitAll()  
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .successHandler((request, response, authentication) -> {
  
                    String role = authentication.getAuthorities().toString();
                    if (role.contains("ADMIN")) {  
                        response.sendRedirect("/admin");  
                    } else if (role.contains("USER")) {
                        response.sendRedirect("/user");  
                    } else {
                        response.sendRedirect("/home");  
                    }
                })
            )
            .logout(logout -> logout
                .logoutUrl("/logout")  
                .logoutSuccessUrl("/login?logout")  
                .invalidateHttpSession(true)  
                .clearAuthentication(true)  
                .permitAll()
            )
            .httpBasic(Customizer.withDefaults())
            .build();
    }
}
