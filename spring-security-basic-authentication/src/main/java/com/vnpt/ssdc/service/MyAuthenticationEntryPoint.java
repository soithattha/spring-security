/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.ssdc.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author soithattha
 * @date Sep 20, 2018
 * @version
 * @description
 */
@Component("restAuthenticationEntryPoint")
public class MyAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private String REALM = "soithatha";

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        // voi cac request khong xac thuc thanh cong, du lieu se duoc tra ve o day
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + REALM + "");
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 : " + authException.getMessage());

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName(REALM);
        super.afterPropertiesSet(); //To change body of generated methods, choose Tools | Templates.
    }

}
