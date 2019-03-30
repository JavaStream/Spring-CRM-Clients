package com.javastream.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// При старте приложения конфигурирует Security
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                                   // Включаем авторизацию для обьекта
                    .antMatchers("/").permitAll()          // Разрешаем доступ без пароля для главной страницы
                    .anyRequest().authenticated()                      // Для всех остальных запросов требуется авторизация
                .and()
                    .formLogin()                                       // Включаем форму для ввода kогина
                    .loginPage("/login")                               // url для ввода kогина
                    .permitAll()                                       // разрешаем этим пользоваться всем
                .and()
                    .logout()                                           // Включаем логаут
                    .permitAll();                                       // разрешаем этим пользоваться всем
    }


    // Менеджер, который обслуживает учетные записи пользователей
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("u")
                        .password("p")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}