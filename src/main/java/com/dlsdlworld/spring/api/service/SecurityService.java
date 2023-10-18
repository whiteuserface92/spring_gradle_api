package com.dlsdlworld.spring.api.service;

import com.dlsdlworld.spring.api.types.PermissionTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

@Component("security")
public class SecurityService {
    private static final Logger log = LoggerFactory.getLogger(SecurityService.class);

    public SecurityService() {
    }

    public boolean hasPermission(PermissionTypes... permissionTypes) {
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        log.trace("permissionTypes:{}", Arrays.toString(permissionTypes));
        log.trace("userAuthorities:{}", userAuthorities);
        Iterator var3 = userAuthorities.iterator();

        while(var3.hasNext()) {
            GrantedAuthority authority = (GrantedAuthority)var3.next();
            String userPermission = authority.getAuthority();
            if (userPermission.equals(PermissionTypes.ALL.getCode())) {
                return true;
            }

            PermissionTypes[] var6 = permissionTypes;
            int var7 = permissionTypes.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                PermissionTypes target = var6[var8];
                if (userPermission.equals(target.getCode())) {
                    return true;
                }

                String userResourceType = userPermission.substring(0, userPermission.length() - 1);
                String userAccessType = userPermission.substring(userPermission.length() - 1);
                String targetResourceType = target.getCode().substring(0, target.getCode().length() - 1);
                String targetAccessType = target.getCode().substring(target.getCode().length() - 1);
                if (userResourceType.equals(targetResourceType) && Integer.parseInt(userAccessType) > Integer.parseInt(targetAccessType)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Long getUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails)authentication.getPrincipal();
                log.info("###KKH_LOG getUserId if : {}", springSecurityUser.getUsername());
                return com.dlsdlworld.spring.api.security.UserDetails.readUserDetails(springSecurityUser.getUsername()).getUserId();
            }

            if (authentication.getPrincipal() instanceof String) {
                log.info("###KKH_LOG getUserId else if : {}", (String)authentication.getPrincipal());
                return com.dlsdlworld.spring.api.security.UserDetails.readUserDetails((String)authentication.getPrincipal()).getUserId();
            }
        } catch (Exception var3) {
            throw new UsernameNotFoundException("Invalid user principal", var3);
        }

        return 0L;
    }

    public com.dlsdlworld.spring.api.security.UserDetails getUsername() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails)authentication.getPrincipal();
                return com.dlsdlworld.spring.api.security.UserDetails.readUserDetails(springSecurityUser.getUsername());
            } else {
                return authentication.getPrincipal() instanceof String ? com.dlsdlworld.spring.api.security.UserDetails.readUserDetails((String)authentication.getPrincipal()) : null;
            }
        } catch (Exception var3) {
            throw new UsernameNotFoundException("Invalid user principal", var3);
        }
    }
}
