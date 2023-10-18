package com.dlsdlworld.spring.api.utils;

import com.dlsdlworld.spring.api.context.Profiles;
import com.dlsdlworld.spring.api.context.Properties;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
@Slf4j
public class PropertySourceUtils {

    /**
     * @param context
     * @return
     */
    @Synchronized
    public static PropertySource getEffectiveProperty(ConfigurableApplicationContext context) {
        final ConfigurableEnvironment env = context.getEnvironment();
        final String cloudConfigName = getSpringCloudConfigName(env);
        final String[] profiles = env.getActiveProfiles();
        log.info("profiles:{}", String.join(",", profiles));
        if (profiles == null || profiles.length < 2)
            throw new IllegalArgumentException("At least two profiles must be specified");

        log.trace("profiles:{}", String.join(",", profiles));
        log.trace("cloudConfigName:{}", cloudConfigName);
        AtomicReference<PropertySource> effectiveProperty = new AtomicReference();

        //use native properties
        if (profiles[0].equals(Profiles.NATIVE)
                && org.apache.commons.lang3.StringUtils.isEmpty(cloudConfigName)) {
            env.getPropertySources().forEach(ps -> {
                log.trace("propertySource:{}", ps);
                if (ps.getName().contains("application-" + profiles[1] + ".properties")) {
                    effectiveProperty.set(ps);
                }
            });
        }
        //use cloud config yml
        else if ((profiles[0].equals(Profiles.DOCKER)
                || profiles[0].equals(Profiles.DOCKER_TEST))
                && !StringUtils.isEmpty(cloudConfigName)) {
            env.getPropertySources().forEach(ps -> {
                if (ps.getName().equals("bootstrapProperties")
                        && ps instanceof CompositePropertySource) {
                    ((CompositePropertySource) ps).getPropertySources().forEach(ps1 -> {
                        if (ps1 instanceof CompositePropertySource
                                && ps1.getName().equals("configService"))
                            ((CompositePropertySource) ps1).getPropertySources().forEach(ps2 -> {
                                if (ps2.getName().contains(cloudConfigName + "-" + profiles[1] + ".properties")) {
                                    effectiveProperty.set(ps2);
                                }
                            });
                    });
                }
            });
        }

        return effectiveProperty.get();
    }

    /**
     * get spring cloud config name
     *
     * @param env
     * @return
     */
    public static String getSpringCloudConfigName(ConfigurableEnvironment env) {
        for (PropertySource ps : env.getPropertySources()) {
            if (ps.getName().startsWith("applicationConfig: [classpath:/bootstrap.yml]")) {
                for (String name : ((EnumerablePropertySource) ps).getPropertyNames()) {
                    if (name.equals("spring.cloud.config.name")) {
                        return ps.getProperty(name).toString();
                    }
                }
            }
        }

        return null;
    }

    /**
     * set server properties
     *
     * @param propertySource
     * @param properties
     */
    public static void setServerProperties(PropertySource propertySource
            , Map<String, Object> properties) {
        if (propertySource instanceof EnumerablePropertySource) {
            for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                if (name.equals(Properties.CONFIG_PORT)) {
                    properties.put("server.port", propertySource.getProperty(name).toString().trim());
                }
            }
        }
        properties.put("eureka.instance.preferIpAddress", "true");
    }

    /**
     * set eureka client properties
     *
     * @param propertySource
     * @param properties
     */
    public static void setEurekaProperties(PropertySource propertySource
            , Map<String, Object> properties) {
        if (propertySource instanceof EnumerablePropertySource) {
            for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                if (name.equals(Properties.CONFIG_DISCOVERY_SERVICE_URL)) {
                    final String value = propertySource.getProperty(name).toString().trim();
                    if (value == null || value.isEmpty()) {
                        properties.put("eureka.client.enabled", false);
                    } else {
                        properties.put("eureka.client.enabled", true);
                        properties.put("eureka.client.serviceUrl.defaultZone", value);
                    }
                }
            }
        }
    }

    /**
     * set rest properties
     *
     * @param propertySource
     * @param properties
     */
    public static void setRestProperties(PropertySource propertySource, Map<String, Object> properties) {
        if (propertySource instanceof EnumerablePropertySource) {
            for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                if (name.equals(Properties.CONFIG_PAGE_SIZE)) {//페이지 로우 카운트
                    final String value = propertySource.getProperty(name).toString().trim();
                    //기본 경로로 '/'로 사용 RepositoryRestResource 에 자동 적용된 Path에 대해서 루트 패스워드 적용
                    properties.put("spring.data.rest.base-path", "/");
                    //repository를 찾는 전략으로 annotation을 사용한다.
                    properties.put("spring.data.rest.detection-strategy", "ANNOTATED");
                    properties.put("spring.data.rest.default-page-size", value);
                    //TODO 설정으로 뺄 필요가 있는지 검토해본다
                    properties.put("spring.data.rest.max-page-size", Integer.MAX_VALUE);
                    //application/json 요청에서 hal+json을 사용하지 않는다.
                    properties.put("spring.data.rest.use-hal-as-default-json-media-type", false);
                    //기본 미디어 타입을 application/json으로 지정.
                    properties.put("spring.data.rest.default-media-type", "application/json");
                    //아래 두가지 설정을 통해 헨들러를 찾을 수 없을때 NoHandlerFound exception을 발생시킴
                    properties.put("spring.mvc.throw-exception-if-no-handler-found", true);
                    properties.put("spring.resources.add-mappings", false);
                }
            }
        }
    }

    /**
     * set data properties
     *
     * @param propertySource
     * @param properties
     * @param profiles
     */
    public static void setDataProperties(PropertySource propertySource
            , Map<String, Object> properties, String[] profiles) {
        if (propertySource instanceof EnumerablePropertySource) {
            for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                final String value = propertySource.getProperty(name).toString().trim();
                if (name.equals(Properties.CONFIG_DATASOURCE_URL)) {//데이터소스 주소
                    //데이터소스 주소가 유닛 테스트용이면 log4jdbc 적용함
                    if (Arrays.stream(profiles).anyMatch(Profiles.LOG4JDBC::equals)) {
                        properties.put("spring.datasource.url", "jdbc:log4jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
                    } else {
                        properties.put("spring.datasource.url", value);
                    }

                    properties.put("spring.datasource.type", "com.zaxxer.hikari.HikariDataSource");
                    //LazyInitializationException을 올바르게 처리 할 수 ​​있도록 OSIV가 비활성한다
                    properties.put("spring.jpa.open-in-view", false);
                    //서버 시작시 DDL문을 생성하지 않는다
//                    properties.put("spring.jpa.generate-ddl", false);
//                    if (Arrays.stream(profiles).anyMatch(LemonProfiles.DDLAUTO::equals)
//                        && propertySource.getProperty(LemonProperties.CONFIG_DATASOURCE_DRIVER).equals("org.h2.Driver")) {
                    if (propertySource.getProperty(Properties.CONFIG_DATASOURCE_DRIVER).equals("org.h2.Driver")) {
                        properties.put("spring.jpa.hibernate.ddl-auto", "create-drop");
                        properties.put("spring.datasource.initialization-mode", "always");
                        properties.put("spring.datasource.sql-script-encoding", "UTF-8");
                        properties.put("spring.jpa.generate-ddl", true);
                    } else {//create_db 프로파일이 아니면 db 검증만 한다
                        properties.put("spring.jpa.hibernate.ddl-auto", "validate");
                        properties.put("spring.datasource.initialization-mode", "never");
                    }

                    if (Arrays.stream(profiles).anyMatch(Profiles.TRACE::equals)) {//trace 프로파일이면
                        //hibernate sql 로그를 출력한다
                        properties.put("spring.jpa.properties.hibernate.show_sql", true);
                        properties.put("spring.jpa.properties.hibernate.format_sql", true);
                        properties.put("spring.jpa.properties.hibernate.use_sql_comments", true);
                    }
                } else if (name.equals(Properties.CONFIG_DATASOURCE_DRIVER)) {//데이터소스 드라이버
                    properties.put("spring.datasource.driver-class-name", value);

                    if (value.equals("org.h2.Driver")) {
                        properties.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.H2Dialect");
                        properties.put("spring.datasource.platform", "h2");
                        properties.put("spring.h2.console.enabled", true);
                        properties.put("spring.h2.console.path", "/h2");

                        if (Arrays.stream(profiles).anyMatch(Profiles.LOG4JDBC::equals)) {
                            properties.put("spring.datasource.driver-class-name", "net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
                        } else {
                            properties.put("spring.datasource.driver-class-name", value);
                        }
                    } else if (value.equals("org.postgresql.Driver")) {
                        properties.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
                        properties.put("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation", true);
                        properties.put("spring.datasource.platform", "PostgreSQL");
                    } else {
                        throw new RuntimeException("database driver " + value + " not supported");
                    }
                } else if (name.equals(Properties.CONFIG_DATASOURCE_USER)) {
                    properties.put("spring.datasource.username", value);
                } else if (name.equals(Properties.CONFIG_DATASOURCE_PASSWORD)) {
                    properties.put("spring.datasource.password", value);
                } else if (name.equals(Properties.CONFIG_DATASOURCE_POOL_SIZE)) {
                    properties.put("spring.datasource.hikari.maximum-pool-size", value);
                }
            }
        }
    }

    /**
     * set security properties
     *
     * @param propertySource
     * @param properties
     */
    public static void setSecurityProperties(PropertySource propertySource
            , Map<String, Object> properties) {
        if (propertySource instanceof EnumerablePropertySource) {
            for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                if (name.equals(Properties.CONFIG_RSA_KEY_VALUE)) {
                    final String value = propertySource.getProperty(name).toString().trim();
                    properties.put("security.sessions", "stateless");
                    properties.put("security.oauth2.resource.jwt.key-value", value);
                }
//                if (name.equals(LemonProperties.CONFIG_OAUTH_CLIENT_ID)) {
//                    final String value = propertySource.getProperty(name).toString().trim();
//                    properties.put("security.oauth2.client.client-id", value);
//                }
//                if (name.equals(LemonProperties.CONFIG_OAUTH_CLIENT_SECRET)) {
//                    final String value = propertySource.getProperty(name).toString().trim();
//                    properties.put("security.oauth2.client.client-secret", value);
//                    properties.put("security.oauth2.client.scope", "read,write");
//                    properties.put("security.oauth2.client.auto-approve-scopes", ".*");
//                }
            }
        }
    }

    /**
     * set ui application properties
     *
     * @param propertySource
     * @param properties
     */
    public static void setUiProperties(PropertySource propertySource
            , Map<String, Object> properties) {
        //lemoncare.context-path 적용 (필수 아님)
        if (propertySource instanceof EnumerablePropertySource) {
            for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                if (name.equals(Properties.CONFIG_SERVER_CONTEXT_PATH)) {
                    properties.put("server.servlet.context-path", propertySource.getProperty(name).toString().trim());
                }
                if (name.equals(Properties.CONFIG_SECURITY_ENABLED)) {
                    if (propertySource.getProperty(name).toString().equals("false")) {
                        properties.put("spring.autoconfigure.exclude", "org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration, org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration");
                    }
                }
            }
        }
        properties.put("spring.thymeleaf.cache", false);
    }

    /**
     * set json properties
     *
     * @param propertySource
     * @param properties
     */
    public static void setJsonProperties(PropertySource propertySource
            , Map<String, Object> properties) {
        properties.put("spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS", false);
    }

    /**
     * set zuul properties
     *
     * @param propertySource
     * @param properties
     */
    public static void setZuulProperties(PropertySource propertySource, Map<String, Object> properties) {
        if (propertySource instanceof EnumerablePropertySource) {
            for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                final String value = propertySource.getProperty(name).toString().trim();
                if (name.matches(Properties.CONFIG_ROUTES_URL_PATTERN)) {
                    String routeName = name.split("\\.")[2];
                    properties.put(String.format("zuul.routes.%s.url", routeName), value);
                    properties.put(String.format("zuul.routes.%s.sensitive-headers", routeName), "");
                } else if (name.matches(Properties.CONFIG_ROUTES_PATH_PATTERN)) {
                    String routeName = name.split("\\.")[2];
                    properties.put(String.format("zuul.routes.%s.path", routeName), value);
                }
                /**
                 * zull timeout 설정 (공통으로 60초)
                 */
                properties.put("zuul.host.connect-timeout-millis", 600000);  // 연결시간 초과 (밀리세컨드)
                properties.put("zuul.host.socket-timeout-millis", 600000);   // 소켓시간 초과 (밀리세컨드)
                properties.put("zuul.host.time-unit", MILLISECONDS);        // 시간단위
                properties.put("zuul.host.time-to-live", 10000);            // TTL
                properties.put("zuul.sslHostnameValidationEnabled", false); // zuul ssl
                /**
                 * Insu 청구할때 리드타임아웃 발생한다고 하여, 여러가지 테스트 중 해당 설정으로만 성공하였으나,
                 * 이게 왜 되는지는 잘모르겠음
                 */
                properties.put("zuul.retryable", true);
            }
        }
    }

    /**
     * set email properties
     *
     * @param propertySource
     * @param properties
     */
    public static void setEmailProperties(PropertySource propertySource, Map<String, Object> properties) {
        if (propertySource instanceof EnumerablePropertySource) {
            for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                final String value = propertySource.getProperty(name).toString().trim();
                if (name.matches(Properties.CONFIG_MAIL_HOST)) {
                    properties.put("spring.mail.host", value);
                } else if (name.matches(Properties.CONFIG_MAIL_PORT)) {
                    properties.put("spring.mail.port", value);
                } else if (name.matches(Properties.CONFIG_MAIL_USERNAME)) {
                    properties.put("spring.mail.username", value);
                } else if (name.matches(Properties.CONFIG_MAIL_PASSWORD)) {
                    properties.put("spring.mail.password", value);
                } else if (name.matches(Properties.CONFIG_MAIL_AUTH_ENABLED)) {
                    properties.put("spring.mail.properties.mail.smtp.auth", value);
                } else if (name.matches(Properties.CONFIG_MAIL_CONNECTION_TIMEOUT)) {
                    properties.put("spring.mail.properties.mail.smtp.connectiontimeout", value);
                } else if (name.matches(Properties.CONFIG_MAIL_TIMEOUT)) {
                    properties.put("spring.mail.properties.mail.smtp.timeout", value);
                } else if (name.matches(Properties.CONFIG_MAIL_WRITE_TIMEOUT)) {
                    properties.put("spring.mail.properties.mail.smtp.writetimeout", value);
                } else if (name.matches(Properties.CONFIG_MAIL_TLS_ENABLED)) {
                    properties.put("spring.mail.properties.mail.smtp.starttls.enable", value);
                } else if (name.matches(Properties.CONFIG_MAIL_DEBUG_ENABLED)) {
                    properties.put("spring.mail.properties.mail.debug", value);
                }
            }
        }
    }

    /*public static void setLemonProperties(PropertySource propertySource, Map<String, Object> properties) {
        if (propertySource instanceof EnumerablePropertySource) {
            for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                if (name.startsWith(LemonProperties.CONFIG_PREFIX)) {
                    final String value = propertySource.getProperty(name).toString().trim();
                    properties.put(name, value);
                }
            }
        }
    }*/
}
