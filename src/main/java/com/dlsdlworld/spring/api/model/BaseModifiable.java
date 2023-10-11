package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.utils.SecurityLoginUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import java.time.LocalDateTime;


/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseModifiable extends BaseCreatable {

    /** 수정일시*/
    private LocalDateTime updatedOn;

    /** 수정자ID*/
    private Long updatedBy;

    /** 수정자명*/
    @Transient
    private String updatedByName;

    @Override
    @PrePersist
    public   void prePersist() {
        super.prePersist();
        this.updatedOn = LocalDateTime.now();

        // 로그인 사용자의 ID, 로그인 전이면 1로 리턴되서 입력됨
        this.updatedBy =  SecurityLoginUtils.getCurrentUserId();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }

}
