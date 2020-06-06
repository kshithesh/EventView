package com.eventview.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("${user.name1}")
                .password(getPasswordEncoder().encode("${admin.password1}"))
                .roles("${role.admin}}")
                .and()
                .withUser("${user.name2}")
                .password(getPasswordEncoder().encode("${user.name2}"))
                .roles("${role.user}");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/users/**,/events/**,/eventtypes/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/event/**", "/event/type/**", "/actuator/**").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .and().httpBasic();
    }
}
