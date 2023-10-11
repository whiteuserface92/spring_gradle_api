package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 1:32 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BasePushMessage extends BaseModifiable {

    /**
     * 메시지내용
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String contents;

    /**
     * 가공된 푸시메시지
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String fcmContents;
}
