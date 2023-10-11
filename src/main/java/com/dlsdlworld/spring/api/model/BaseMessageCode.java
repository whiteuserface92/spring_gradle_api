package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * 응답코드관리(message_def)
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseMessageCode extends BaseModifiable {

    /**
     * 서비스구분
     */
    @Column(length = Columns.svcCd, nullable = false)
    private String svcCd;

    /**
     * 응답기관코드
     */
    @Column(length = Columns.compId, nullable = false)
    private String compId;

    /**
     * 응답코드
     */
    @Column(length = Columns.replyCd, nullable = false)
    private String replyCd;

    /**
     * 응답메시지
     */
    @Column(length = Columns.replyMsg, nullable = false)
    private String replyMsg;

    /**
     * 사용자메시지
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String userMsg;

    /**
     * 처리방법 안내
     */
    @Column(length = Columns.guideMsg)
    private String guideMsg;

    /**
     * 상담응대메시지
     */
    @Column(length = Columns.csMsg)
    private String csMsg;

    /**
     * 사용여부
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 유효시작일
     */
    @Column(nullable = false)
    private LocalDate strDt;

    /**
     * 유효종료일
     */
    @Column(nullable = false)
    private LocalDate endDt;

}
