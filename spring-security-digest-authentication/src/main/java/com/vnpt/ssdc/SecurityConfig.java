/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.ssdc;

import com.vnpt.ssdc.service.UserDeailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

/**
 *
 * @author soithattha
 * @date Sep 15, 2018
 * @version
 * @description
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDeailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilter(digestAuthenticationFilter()) // register digest entry point
                .exceptionHandling().authenticationEntryPoint(digestEntryPoint()) // on exception ask for digest authentication
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll() // /home will be accessible directly, no need of any authentication
                .anyRequest().authenticated().and()
                .exceptionHandling().accessDeniedPage("/403");

    }

    DigestAuthenticationFilter digestAuthenticationFilter() throws Exception {
        DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
        digestAuthenticationFilter.setUserDetailsService(userDetailsService);
        digestAuthenticationFilter.setAuthenticationEntryPoint(digestEntryPoint());
        return digestAuthenticationFilter;
    }

    @Bean
    DigestAuthenticationEntryPoint digestEntryPoint() { 
        DigestAuthenticationEntryPoint dAuth = new DigestAuthenticationEntryPoint();
        dAuth.setRealmName("soithatha");
        dAuth.setKey("123456");
        return dAuth;
    }

}
