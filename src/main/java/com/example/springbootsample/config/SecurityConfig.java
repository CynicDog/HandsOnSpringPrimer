package com.example.springbootsample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Set an out-of-security target
    @Override
    public void configure(WebSecurity web) throws Exception {
     web.ignoring()
             .antMatchers("/h2-console/**")
             .antMatchers("/js/**")
             .antMatchers("/css/**")
             .antMatchers("/webjars/**");
    }

    // Set various security settings
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Accessible pages without log-in
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/signup").permitAll()
                .anyRequest().authenticated(); // Otherwise no direct linking is not allowed

        http.csrf().disable();
    }
}
