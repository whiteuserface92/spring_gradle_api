package com.dlsdlworld.spring.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProfileHelper {

    @Value("${spring.profiles.active:}")
    private String activeProfiles;

    public String[] getActiveProfiles() {
        String[] profiles = activeProfiles.split(",");
        for (String profileName : profiles) {
            log.trace("Currently active profile - " + profileName);
        }
        return profiles;
    }

    public boolean isActive(String strActive) {
        return activeProfiles.toLowerCase().contains(strActive.toLowerCase());
    }

    public boolean isProd() {
        return isActive("prod");
    }

}
