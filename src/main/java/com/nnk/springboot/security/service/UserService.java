package com.nnk.springboot.security.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}
