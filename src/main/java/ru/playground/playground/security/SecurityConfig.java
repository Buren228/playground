package ru.playground.playground.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.playground.playground.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Создаем бин для настройки фильтра безопасности
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/register").permitAll()  // Страницы регистрации и входа открыты
                        .requestMatchers("/admin/**").hasRole("ADMIN")   // Доступ только для администраторов
                        .anyRequest().authenticated()  // Для остальных страниц нужен вход
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Указываем путь до нашей страницы логина
                        .permitAll()  // Страница доступна для всех
                        .defaultSuccessUrl("/login-success", true)  // Перенаправление после успешного входа
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // URL для выхода
                        .logoutSuccessUrl("/logout-success")  // Перенаправление после выхода
                        .permitAll()
                );

        return http.build(); // Возвращаем построенную конфигурацию
    }

    // Создаем бин для AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
