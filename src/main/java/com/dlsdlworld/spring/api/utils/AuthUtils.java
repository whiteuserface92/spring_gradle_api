package com.dlsdlworld.spring.api.utils;

import com.dlsdlworld.spring.api.types.AuthTypes;
import com.dlsdlworld.spring.api.types.LoginTypes;
import com.dlsdlworld.spring.api.types.PermissionTypes;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthUtils {
    public AuthUtils() {
    }

    public static List<GrantedAuthority> anonymousAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(PermissionTypes.HOSPITAL_READ.getCode()), new SimpleGrantedAuthority(PermissionTypes.MESSAGE_READ.getCode()), new SimpleGrantedAuthority(PermissionTypes.USER_WRITE.getCode()), new SimpleGrantedAuthority(PermissionTypes.APP_READ.getCode()), new SimpleGrantedAuthority(PermissionTypes.AUTHORITY_READ.getCode()), new SimpleGrantedAuthority(PermissionTypes.TERMS_READ.getCode()));
    }

    public static AuthTypes getAuthType(LoginTypes loginType) {
        switch (loginType) {
            case PATTERN:
            case PIN:
            case FACE:
            case FINGER:
                return AuthTypes.SIMPLE;
            case APPLE:
            case KAKAO:
            case GOOGLE:
            case NAVER:
            case LEGACY:
                return AuthTypes.SNS;
            case PASSWORD:
                return AuthTypes.PASSWORD;
            default:
                throw new IllegalArgumentException(loginType.name() + " is not supported");
        }
    }

    public static String[] permitAllUrls() {
        return new String[]{"/", "/oauth/token", "/rest/signup", "/h2/**", "/favicon.ico"};
    }
}
