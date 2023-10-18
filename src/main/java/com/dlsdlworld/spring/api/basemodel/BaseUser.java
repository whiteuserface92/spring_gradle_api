package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.converter.JoinTypeConverter;
import com.dlsdlworld.spring.api.basemodel.Columns;
import com.dlsdlworld.spring.api.types.GenderTypes;
import com.dlsdlworld.spring.api.types.JoinTypes;
import com.dlsdlworld.spring.api.types.TelecomTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 사용자관리
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUser extends BaseModifiable {

    /**
     * 성별
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.sex)
    private GenderTypes sex;

    /**
     * 이름
     */
    @Column(length = Columns.userNm)
    private String userNm;

    /**
     * 생년월일
     */
    @Column(length = Columns.birthday)
    private String birthday;

    /**
     * 이메일
     */
    @Column(length = Columns.email)
    private String email;

    /**
     * 휴대폰번호
     */
    @Column(length = Columns.phoneNo)
    private String phoneNo;

    /**
     * 우편번호
     */
    @Column(length = Columns.postCd)
    private String postNo;

    /**
     * 도로명주소
     */
    @Column(length = Columns.addr)
    private String addr1;

    /**
     * 상세주소
     */
    @Column(length = Columns.addr)
    private String addr2;

    /**
     * 회원가입방법
     */
    @Convert(converter = JoinTypeConverter.class)
    @Column(length = Columns.joinType, nullable = false)
    private JoinTypes joinType;

    /**
     * 본인 CI
     */
    @Column(length = Columns.ci)
    private String myCi;

    /**
     * 대리인 CI
     */
    @Column(length = Columns.ci)
    private String representCi;

    /**
     * 병원코드
     */
    @Column(length = Columns.hospitalCd)
    private String hospitalCd;

    /**
     * 통신사코드
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.telecomCd)
    private TelecomTypes telecomCd;

    /**
     * 국가코드
     */
    @Column(length = Columns.countryCd)
    private String countryCd;

    /**
     * 통합마일리지
     */
    @Column
    private Integer mileage;

    /**
     * 사용여부
     */
    @Column(nullable = false)
    private Boolean enabled;
}
