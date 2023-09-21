package com.dlsdlworld.spring.api.service;

import com.dlsdlworld.spring.api.repository.ApiRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    @Resource(name="apiRepository")
    private ApiRepository apiRepository;

}
