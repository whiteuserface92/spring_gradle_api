package com.dlsdlworld.spring.api.context;

public class Properties {
    public static final String CONFIG_PREFIX = "common";
    public static final String CONFIG_PORT = CONFIG_PREFIX + ".port";
    public static final String CONFIG_DISCOVERY_SERVICE_URL = CONFIG_PREFIX + ".discovery-service-url";
    public static final String CONFIG_PAGE_SIZE = CONFIG_PREFIX + ".default-page-size";
    public static final String CONFIG_DATASOURCE_DRIVER = CONFIG_PREFIX + ".datasource-driver";
    public static final String CONFIG_DATASOURCE_URL = CONFIG_PREFIX + ".datasource-url";
    public static final String CONFIG_DATASOURCE_USER = CONFIG_PREFIX + ".datasource-user";
    public static final String CONFIG_DATASOURCE_PASSWORD = CONFIG_PREFIX + ".datasource-password";
    public static final String CONFIG_DATASOURCE_POOL_SIZE = CONFIG_PREFIX + ".datasource-pool-size";
    public static final String CONFIG_RSA_KEY_VALUE = CONFIG_PREFIX + ".rsa-key-value";
    public static final String CONFIG_REDIS_HOSTS = CONFIG_PREFIX + ".redis-hosts";
    public static final String CONFIG_REDIS_PASSWORD = CONFIG_PREFIX + ".redis-password";
    public static final String CONFIG_API_CONNECT_TIMEOUT = CONFIG_PREFIX + ".api-connect-timeout";
    public static final String CONFIG_API_READ_TIMEOUT = CONFIG_PREFIX + ".api-read-timeout";
    public static final String CONFIG_ROUTES_PATH_PATTERN = "^common\\.routes\\.[a-zA-Z-_]+\\.path$";
    public static final String CONFIG_ROUTES_URL_PATTERN = "^common\\.routes\\.[a-zA-Z-_]+\\.url$";
    public static final String CONFIG_SERVER_CONTEXT_PATH = CONFIG_PREFIX + ".context-path";
    //    public static final String CONFIG_OAUTH_CLIENT_ID = CONFIG_PREFIX + ".oauth-client-id";
//    public static final String CONFIG_OAUTH_CLIENT_SECRET = CONFIG_PREFIX + ".oauth-client-secret";
    //pay properties
    public static final String CONFIG_PAY_CLOUD_ENABLED = CONFIG_PREFIX + ".pay-cloud.enabled";
    public static final String CONFIG_PAY_CLOUD_URL = CONFIG_PREFIX + ".pay-cloud.url";
    public static final String CONFIG_SECURITY_ENABLED = CONFIG_PREFIX + ".security.enabled";
    public static final String CONFIG_WEB_MODULE = CONFIG_PREFIX + ".web.module";
    public static final String CONFIG_WEB_PROD_CD = CONFIG_PREFIX + "web.prod-cd";
    public static final String CONFIG_WEB_SECURE_COOKIE = CONFIG_PREFIX + "web.secure-cookie";

    public static final String CONFIG_MAIL_AUTH_ENABLED = CONFIG_PREFIX + ".mail-auth-enabled";
    public static final String CONFIG_MAIL_TLS_ENABLED = CONFIG_PREFIX + ".mail-tls-enabled";
    public static final String CONFIG_MAIL_DEBUG_ENABLED = CONFIG_PREFIX + ".mail-debug-enabled";
    public static final String CONFIG_MAIL_HOST = CONFIG_PREFIX + ".mail-host";
    public static final String CONFIG_MAIL_PORT = CONFIG_PREFIX + ".mail-port";
    public static final String CONFIG_MAIL_USERNAME = CONFIG_PREFIX + ".mail-username";
    public static final String CONFIG_MAIL_PASSWORD = CONFIG_PREFIX + ".mail-password";
    public static final String CONFIG_MAIL_CONNECTION_TIMEOUT = CONFIG_PREFIX + ".mail-connection-timeout";
    public static final String CONFIG_MAIL_WRITE_TIMEOUT = CONFIG_PREFIX + ".mail-write-timeout";
    public static final String CONFIG_MAIL_TIMEOUT = CONFIG_PREFIX + ".mail-timeout";

//    public static final String CONFIG_ACCESS_LOG_ENABLED = CONFIG_PREFIX + ".access-log-enabled";
}
