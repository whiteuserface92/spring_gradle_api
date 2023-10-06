package com.dlsdlworld.spring.api.service;

import com.dlsdlworld.spring.api.repository.ApiRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiService {

    @Resource(name="apiRepository")
    private ApiRepository apiRepository;

}
