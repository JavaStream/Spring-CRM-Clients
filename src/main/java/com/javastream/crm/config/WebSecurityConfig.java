package com.javastream.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

// При старте приложения конфигурирует Security
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                                   // Включаем авторизацию для обьекта
                    .antMatchers("/", "/registration").permitAll()          // Разрешаем доступ без пароля для главной страницы
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())                 // шифрует пароли, чтобы они не хранились в явном виде
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id=ur.user_id where u.username=?");

    }
}