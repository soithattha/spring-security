/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.ssdc.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author kiendt
 */
@Service("userDetailsService")
public class UserDeailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if ("soithattha".equals(userName)) {
            return User.withUsername(userName).roles("USER").password("123456").build();
        } else {
            throw new UsernameNotFoundException(userName);
        }
    }

}
