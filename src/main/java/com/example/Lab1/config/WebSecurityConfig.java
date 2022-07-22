package com.example.Lab1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/station/create").hasRole("ADMIN")
                .antMatchers("/station/edit").hasRole("ADMIN")
                .antMatchers("/train/create").hasRole("ADMIN")
                .antMatchers("/train/edit").hasRole("ADMIN")
                .antMatchers("/route/create").hasRole("ADMIN")
                .antMatchers("/route/edit").hasRole("ADMIN")
                .antMatchers("/carriage/create").hasRole("ADMIN")
                .antMatchers("/carriage/edit").hasRole("ADMIN")
                .antMatchers("/trip/create").hasRole("ADMIN")
                .antMatchers("/trip/edit").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedPage("/access/denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder())
                .usersByUsernameQuery("select username, password, active from user_entity where username = ?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from user_entity u inner join user_entity_roles ur on u.id = ur.user_entity_id where u.username=?");
    }
}