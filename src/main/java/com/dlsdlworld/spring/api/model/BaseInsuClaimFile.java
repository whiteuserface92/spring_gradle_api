package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * 실손첨부파일
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/21
 * Time : 2:27 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuClaimFile extends BasePersistable {

    /**
     * 증빙사진 upload경로
     */
    @Column(length = Columns.serviceUrl, nullable = false)
    private String uploadUrl;

    /**
     * 전송상태
     */
    @Column(length = Columns.sendStatus)
    private String sendStatus;

    /**
     * 업로드파일이 이미지일 경우 썸네일url
     */
    @Column(length = Columns.serviceUrl)
    private String thumbUrl;

    /**
     * 입력일
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
    }
}
