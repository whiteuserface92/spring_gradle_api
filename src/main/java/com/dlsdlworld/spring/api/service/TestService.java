package com.dlsdlworld.spring.api.service;

import com.dlsdlworld.spring.api.dto.TestTable;
import com.dlsdlworld.spring.api.repository.TestRepository;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TestService {
    @Resource(name="testRepository")
    private TestRepository testRepository;

    public Iterable<TestTable> getTestTable(){
        log.info("TestService run");
        return testRepository.findAll();
    }
}
