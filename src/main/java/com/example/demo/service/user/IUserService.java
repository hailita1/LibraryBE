package com.example.demo.service.user;

import com.example.demo.model.auth.Reader;
import com.example.demo.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<Reader>, UserDetailsService {
    Reader findByEmail(String email);
}
