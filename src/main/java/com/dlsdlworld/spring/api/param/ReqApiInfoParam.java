package com.dlsdlworld.spring.api.param;

import lombok.Data;
import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : hskim
 * Date : 2020/07/07
 * Time : 13:57 오후
 */

@Data
public class ReqApiInfoParam {

    private String url;
    private Map<String, Object> paramMap;
    private HttpMethod method;
}
