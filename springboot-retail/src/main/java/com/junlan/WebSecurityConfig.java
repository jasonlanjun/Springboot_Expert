package com.junlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource datasource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll().antMatchers("/user/new")
                .permitAll().antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MANAGER')")
                .and().formLogin().loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/admin/dashboard")
                .usernameParameter("username").passwordParameter("password").and().logout().logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/login").and().csrf().disable();

        // http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select user_name, password, enabled from user_entity where user_name=?")
                .authoritiesByUsernameQuery("select user_name, user_role from user_roles_entity where user_name=?");

    }
}