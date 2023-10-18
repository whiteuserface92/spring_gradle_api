package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
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
