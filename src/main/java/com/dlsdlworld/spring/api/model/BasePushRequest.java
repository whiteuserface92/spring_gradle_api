package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 푸시요청
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 1:33 오후
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
