package com.example.test.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/role")
    public String getRole(Authentication authentication) {
        return authentication.getAuthorities().iterator().next().getAuthority();
    }
}
