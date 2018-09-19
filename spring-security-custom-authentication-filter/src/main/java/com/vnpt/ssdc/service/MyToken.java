/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.ssdc.service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author soithattha
 * @date Sep 19, 2018
 * @version
 * @description
 */
public class MyToken implements Authentication {

    private String userName;
    private String password;
    private ArrayList grantedAuthorities;
    private boolean isAuthenticated = false;

    public MyToken(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.isAuthenticated = false;
    }

    public MyToken(String userName, String password, ArrayList grantedAuthorities) {
        this.userName = userName;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        isAuthenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getDetails() {
        return new User(userName, password, grantedAuthorities);
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean bln) throws IllegalArgumentException {
        isAuthenticated = bln;
    }

    @Override
    public String getName() {
        return userName;
    }

}
