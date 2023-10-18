package com.dlsdlworld.spring.api.config;

import com.dlsdlworld.spring.api.utils.PropertySourceUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class SecurityContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final Logger log = LoggerFactory.getLogger(SecurityContextInitializer.class);

    public SecurityContextInitializer() {
    }

    public void initialize(ConfigurableApplicationContext applicationContext) {
        PropertySource propertySource = PropertySourceUtils.getEffectiveProperty(applicationContext);
        if (propertySource != null) {
            System.out.println("========================================================================");
            System.out.println("                 C O M M O N S    S E C U R I T Y    C O N T E X T      ");
            System.out.println("========================================================================");
            ConfigurableEnvironment env = applicationContext.getEnvironment();
            Map<String, Object> properties = new HashMap();
            PropertySourceUtils.setSecurityProperties(propertySource, properties);
            MapUtils.debugPrint(System.out, "SecurityProperties", properties);
            env.getPropertySources().addFirst(new MapPropertySource("SecurityProperties", properties));
        }
    }
}
