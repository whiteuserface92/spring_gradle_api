package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.converter.SiteTypeConverter;
import com.dlsdlworld.spring.api.types.SiteTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserFavorite extends BasePersistable {

    /**
     * 단골명
     */
    @Column(length = Columns.favoriteNm, nullable = false)
    private String favoriteNm;

    /**
     * 사이트구분
     */
    @Convert(converter = SiteTypeConverter.class)
    @Column(length = Columns.siteType, nullable = false)
    private SiteTypes siteType;

    /**
     * 사이트구분
     */
    @Column(length = Columns.siteCd, nullable = false)
    private String siteCd;

    /**
     * 팩스번호
     */
    @Column(length = Columns.faxNo)
    private String faxNo;

    /**
     * 수신처이메일
     */
    @Column(length = Columns.email)
    private String email;

    /**
     * 생성일시
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * 수정일시
     */
    @Column
    private LocalDateTime updatedOn;
}
