/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.ssdc.controller;

import com.vnpt.ssdc.service.IsUser;
import com.vnpt.ssdc.service.TestRoleService;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author kiendt
 */
@Controller
public class HomeController {

    @Autowired
    private TestRoleService service;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication());
        return "home";
    }

    @GetMapping("/edit")
    public String edit() {
        service.editSomething();
        return "user";
    }

    @GetMapping("/delete")
    public String delete() {
        service.deleteSomething();
        return "user";
    }

    @GetMapping("/view")
    public String user() {
        service.viewSomething();
        return "user";
    }

}
