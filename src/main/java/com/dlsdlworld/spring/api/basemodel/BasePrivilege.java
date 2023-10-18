package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.converter.PermissionTypeConverter;
import com.dlsdlworld.spring.api.types.PermissionTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

/**
 * 권한정의
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BasePrivilege extends BaseCreatable {

    /**
     * 권한명
     */
    @Convert(converter = PermissionTypeConverter.class)
    @Column(length = Columns.privilegeNm, name = "privilegeNm", nullable = false)
    private PermissionTypes privilegeNm;

    /**
     * 설명
     */
    @Column(length = Columns.privilegeDesc)
    private String privilegeDesc;
}
