package com.nnk.springboot.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * L'interface a une méthode pour charger l'utilisateur par nom d' utilisateur et renvoie un UserDetails objet que Spring Security
 * peut utiliser pour l'authentification et la validation.
 */
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}