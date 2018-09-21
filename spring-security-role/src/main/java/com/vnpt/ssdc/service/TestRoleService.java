/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.ssdc.service;

import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 *
 * @author soithattha
 * @date Sep 19, 2018
 * @version
 * @description
 */
@Service
@RolesAllowed("VIEWER")
public class TestRoleService {

    @RolesAllowed("EDITER")
    public void editSomething() {
        System.out.println("edit something");
    }

    @RolesAllowed("ADMINISTRATOR")
    public void deleteSomething() {
        System.out.println("edit something");
    }

    public void viewSomething() {
        System.out.println("edit something");
    }

}
