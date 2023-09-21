package com.dlsdlworld.spring.api.service;

import com.dlsdlworld.spring.api.dto.TestTable;
import com.dlsdlworld.spring.api.repository.ApiRepository;
import com.dlsdlworld.spring.api.repository.TestRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    @Resource(name="apiRepository")
    private ApiRepository apiRepository;

}
