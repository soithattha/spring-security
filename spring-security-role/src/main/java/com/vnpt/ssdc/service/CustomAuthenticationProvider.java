/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.ssdc.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author soithattha
 * @date Sep 18, 2018
 * @version
 * @description
 */
public class CustomAuthenticationProvider implements AuthenticationProvider, Serializable {

    UserDetailsService userDetailService;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        //  
        try {
            // sử dụng userDetailService để lấy thông tin user
            UserDetails user = userDetailService.loadUserByUsername(a.getName());
            // logic xac thuc user
            MyToken result = null;
            if (user.getUsername().equals(a.getName()) && user.getPassword().equals(a.getCredentials().toString())) {
                result = new MyToken(user.getUsername(), user.getPassword(), (Set) user.getAuthorities());
            }
            return result;
        } catch (UsernameNotFoundException e) {
            throw e;
        }
    }

    public void setUserDetailService(UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public boolean supports(Class<?> type) {
        return MyToken.class.isAssignableFrom(type);
    }

}
