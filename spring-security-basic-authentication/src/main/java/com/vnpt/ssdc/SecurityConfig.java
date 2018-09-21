/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.ssdc;

import com.vnpt.ssdc.service.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

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
    private MyAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("temporary").password("temporary").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // khi add them configure httpBasic() he thong se add them mot filter BasicAuthenticationFilter
        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .formLogin()
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout();

    }

}
