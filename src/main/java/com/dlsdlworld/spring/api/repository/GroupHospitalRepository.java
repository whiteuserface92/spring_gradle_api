package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.baserepository.BaseGroupHospitalRepository;
import com.dlsdlworld.spring.api.model.GroupHospital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 */
public interface GroupHospitalRepository extends BaseGroupHospitalRepository<GroupHospital> {
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    @Query(nativeQuery = true,
            value = "SELECT count(1)  " +
                    "FROM group_mbr a " +
                    "WHERE a.hospital_id = ?1")
    long countGroupHospital(Long id);
}
