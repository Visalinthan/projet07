package com.nnk.springboot.security.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    //Méthode pour charger l'utilisateur par son nom et renvoie un UserDetails qui contient les informations nécessaire pour un user
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}