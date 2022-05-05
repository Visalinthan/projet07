package com.nnk.springboot.security;

import com.nnk.springboot.domain.ERole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
class UserAuthentificationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


        boolean hasUserRole = authorities.stream().filter(r->r.getAuthority().equals(ERole.ROLE_USER.name())).count()>0;
        boolean hasAdminRole = authorities.stream().filter(r->r.getAuthority().equals(ERole.ROLE_ADMIN.name())).count()>0;

        if (hasUserRole) {
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "ruleName/list");
        } else if (hasAdminRole) {
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "user/list");
        } else {
            throw new IllegalStateException();
        }
    }
}
