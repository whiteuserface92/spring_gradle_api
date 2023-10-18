package com.dlsdlworld.spring.api.context;

public class Profiles {
    /**
     * junit 테스트용
     */
    public static final String JUNIT = "junit";

    /**
     * log4jdbc 로깅 사용
     */
    public static final String LOG4JDBC = "log4jdbc";

    /**
     * Trace 로깅 사용
     */
    public static final String TRACE = "trace";

    /**
     * 설정파일을 classpath에서 로딩
     */
    public static final String NATIVE = "native";

    /**
     * 설정파일을 cloud config에서 로딩
     */
    public static final String DOCKER = "docker";

    /**
     * docker 테스트용
     */
    public static final String DOCKER_TEST = "docker_test";

    /**
     * api 리턴메세지 디버깅 용
     */
    public static final String DEBUG = "debug";

    /**
     * ddl 출력용
     */
    public static final String DDLAUTO = "ddlauto";

}
