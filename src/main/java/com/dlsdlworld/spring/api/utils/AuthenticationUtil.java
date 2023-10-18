package com.dlsdlworld.spring.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

@Slf4j
public class AuthenticationUtil {
    //Ensures that this class cannot be instantiated
    private AuthenticationUtil() {
    }

    public static void clearAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public static void configureAuthentication(String role) {
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
        log.info("authorities:{}, role:{}", authorities, role);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "user",
                role,
                authorities
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
