package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.baserepository.BaseUserPatientRepository;
import com.dlsdlworld.spring.api.model.UserPatient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 */
public interface UserPatientRepository extends BaseUserPatientRepository<UserPatient> {

    @Modifying
    @Transactional
    @Query("delete from UserPatient up where up.user.id = ?1 ")
    void deleteByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = " delete from user_patient where user_id = ?1 and patient_no = ?2 and hospital_id = ?3 ")
    void deleteByUserIdAndPatientId(Long userId, String patientId, Long hospitalId);


}
