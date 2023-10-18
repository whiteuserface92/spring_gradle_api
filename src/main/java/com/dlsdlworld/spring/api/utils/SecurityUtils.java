package com.dlsdlworld.spring.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public final class SecurityUtils {

    private SecurityUtils() {}

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user.
     */
    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }

    private static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {  //여기서 getPrincipal String으로 리턴한다.
            return (String) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * Get the JWT of the current user.
     * //OAuth2Authentication [Principal={"appId":1,"authType":"SNS","userAccount":"mplusS","userId":2,"loginType":"SEV","uuid":"1a303f00-fede-4269-8ab4-e259465d5602-mplusS"}, Credentials=[PROTECTED], Authenticated=true, Details=remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>, Granted Authorities=[11, 101, 103, 71, 61, 51, 73, 41, 74, 54]]
     * remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>
     * @return the JWT of the current user.
     */
    public static Optional<String> getCurrentUserJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional
                .ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getDetails() instanceof OAuth2AuthenticationDetails)
                .map(authentication -> ( ( (OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue()) );
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && getAuthorities(authentication).noneMatch(AuthoritiesConstants.ANONYMOUS::equals);
    }

    /**
     * Checks if the current user has a specific authority.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public static boolean hasCurrentUserThisAuthority(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && getAuthorities(authentication).anyMatch(authority::equals);
    }

    private static Stream<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
    }
    /**
     * userId의 값은 현재의 시스템에서 관리하는 사용자 Id USER Id는 JWT 토큰에서 가져 와야 한다?
     *
     * @return
     */

    public static Long getCurrentUserId() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        String userLoginJson  = extractPrincipal(securityContext.getAuthentication()) ; //JSON Format
        // {"appId":1,"authType":"SNS","userAccount":"mplusS","userId":2,"loginType":"SEV","uuid":"1a303f00-fede-4269-8ab4-e259465d5602-mplusS"}
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> usernameMap = new HashMap<>();
        Long retUserId = 0L;
        try {

            com.dlsdlworld.spring.api.security.UserDetails userDetails =   mapper.readerFor(UserDetails.class).readValue(userLoginJson);
  /*
            usernameMap = mapper.readValue(userLoginJson, new TypeReference<Map<String, Object>>(){});
            retUserId =   (Long) usernameMap.get("userId");*/
            retUserId  = userDetails.getUserId();
        } catch (JsonProcessingException e) {
            log.error("parse Error: {}", e.getLocalizedMessage());
            retUserId = 0L;
        }


        return retUserId ;
    }
}

