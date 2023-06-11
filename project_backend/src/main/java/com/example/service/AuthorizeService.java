package com.example.service;

import com.example.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {

    boolean validateAndRegister(String username, String password, String email, String type);
}
