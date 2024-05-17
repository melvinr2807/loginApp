package com.example.loginApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll() // Permitir acceso público a la página de login
                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
                .and()
                .formLogin()
                .loginPage("/login") // Especificar la página de login personalizada
                .defaultSuccessUrl("/", true) // Redirigir al usuario a la página de inicio después de un login exitoso
                .permitAll() // Permitir acceso público al formulario de login
                .and()
                .logout()
                .permitAll(); // Permitir acceso público a la funcionalidad de logout

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user") // Nombre de usuario
                .password("password") // Contraseña
                .roles("USER") // Rol del usuario
                .build());
        return manager;
    }
}
