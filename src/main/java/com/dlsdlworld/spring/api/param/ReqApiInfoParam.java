package com.dlsdlworld.spring.api.param;

import lombok.Data;
import org.springframework.http.HttpMethod;

import java.util.Map;

/**

 */

@Data
public class ReqApiInfoParam {

    private String url;
    private Map<String, Object> paramMap;
    private HttpMethod method;
}
