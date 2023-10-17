/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.configs;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author FPTSHOP
 */
public class CustomSuccessHandler implements AuthenticationSuccessHandler{
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) 
            throws IOException, ServletException {
         // Get the user's authorities (roles)
        Collection<? extends GrantedAuthority> authorities = a.getAuthorities();

        // Check the user's roles and redirect accordingly
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_GVU")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/giaovu");
                return;
            }
             redirectStrategy.sendRedirect(hsr, hsr1, "/");
        }
    }   
}
