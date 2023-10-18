package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 푸시요청
 */
@Getter
@Setter
@MappedSuperclass
public class BasePushRequest extends BasePersistable {

    /**
     * 요청일시
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;
}
