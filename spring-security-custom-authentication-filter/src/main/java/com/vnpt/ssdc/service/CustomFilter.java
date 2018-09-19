/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.ssdc.service;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author soithattha
 * @date Sep 19, 2018
 * @version
 * @description
 */
@Component
public class CustomFilter extends GenericFilterBean {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Autowired
    private AuthenticationManager authenticationManager;

    private AuthenticationSuccessHandler handler = new AuthenticationSuccessHandler() {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse response, Authentication a) throws IOException, ServletException {
            response.sendRedirect("/");
        }
    };

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        AntPathRequestMatcher matcher = new AntPathRequestMatcher("/login", "POST");
        if (!matcher.matches(request)) {
            fc.doFilter(req, res);
            return;
        }

        if (request.getRemoteHost().equals("127.0.0.1")) {

            String username = obtainUsername(request);
            String password = obtainPassword(request);

            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            Authentication auth;
            MyToken authRequest = new MyToken(
                    username, password);
            auth = this.authenticationManager.authenticate(authRequest);
            if (auth == null) {
                return;
            }
            
            handler.onAuthenticationSuccess(request, response, auth);
        }
    }

    protected String obtainUsername(ServletRequest request) {
        return request.getParameter(USERNAME);
    }

    protected String obtainPassword(ServletRequest request) {
        return request.getParameter(PASSWORD);
    }

}
