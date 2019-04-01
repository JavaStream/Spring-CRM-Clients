package com.javastream.crm.config;

import com.javastream.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

// При старте приложения конфигурирует Security
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                                                    // Включаем авторизацию для обьекта
                    .antMatchers("/", "/registration").permitAll()          // Разрешаем доступ без пароля для главной страницы и для Регистрации
                    .anyRequest().authenticated()                                       // Для всех остальных запросов требуется авторизация
                .and()
                    .formLogin()                                       // Включаем форму для ввода kогина
                    .loginPage("/login")                               // url для ввода kогина
                    .permitAll()                                       // разрешаем этим пользоваться всем
                .and()
                    .logout()                                           // Включаем логаут
                    .permitAll();                                       // разрешаем этим пользоваться всем
    }


    // Менеджер, который обслуживает учетные записи пользователей. Проводим аутентификацию через базу данных
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());             // шифрует пароли, чтобы они не хранились в явном виде

    }
}