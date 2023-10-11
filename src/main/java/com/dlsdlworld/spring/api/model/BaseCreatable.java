package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.utils.SecurityLoginUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import java.time.LocalDateTime;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCreatable extends BasePersistable {

    /** 생성일시*/
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;

    /** 생성자식별자*/
    @Column(nullable = false, updatable = false)
    private Long createdBy;

    /** 생성자명*/
    @Transient
    private String createdByName;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
      //  this.createdBy = LemonUserDetails.readLemonUserDetails().getUserId();
        // 회원가입시 anonymousUser 의 경우는 userId가 없음. 여기서 오류발생
        // 일단 1로 하드코딩 (최대길 : 2020-04-25)
        // 전무님 도와주세요.
        // 로그인 사용자의 경우 ID값 id 없는 사용자의 경우 1을 리턴하도록 되어 있다.
        if(SecurityLoginUtils.isAuthenticated()) {
            this.createdBy = SecurityLoginUtils.getCurrentUserId();
        }else  { // 로그인 처리 되어 있지 않으면 제외처리
            this.createdBy = 0L;
        }
    }

}
