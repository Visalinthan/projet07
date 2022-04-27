package com.nnk.springboot.security.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacadeImpl {
    Authentication getAuthentication();
}
