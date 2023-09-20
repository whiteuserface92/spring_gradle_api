package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.dto.TestTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TestRepository extends CrudRepository<TestTable, Long> {

    @Override
    Optional<TestTable> findById(Long aLong);
}
