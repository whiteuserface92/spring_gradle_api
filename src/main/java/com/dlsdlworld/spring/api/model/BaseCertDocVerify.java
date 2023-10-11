package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 제증명문서확인
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/30
 * Time : 7:56 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCertDocVerify implements Serializable {

    /**
     * 문서확인번호(unidocs에서 생성하는 문서확인번호)
     */
    @Id
    @Column(name = "doc_verify_no", length = Columns.docVerifyNo)
    private String docVerifyNo;

    /**
     * 암호화된문서확인번호
     */
    @Column(length = Columns.encDocVerifyNo, nullable = false)
    private String encDocVerifyNo;

    /**
     * 발행일자
     */
    @Column(nullable = false)
    private LocalDateTime issuedOn;

    /**
     * 만료일자
     */
    @Column(nullable = false)
    private LocalDateTime expiredOn;

    /**
     * 입력일자
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * 수정일자
     */
    @Column(nullable = false)
    private LocalDateTime updatedOn;
}
