package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.event.BaseMessageListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(BaseMessageListener.class)
public abstract class BaseMessage extends BaseModifiable {

    /**
     * 언어코드
     */
    @Column(length = Columns.langCd, nullable = false)
    private String langCd;

    /**
     * 메시지코드
     */
    @Column(length = Columns.msgCd, nullable = false)
    private String msgCd;

    /**
     * 메시지
     */
    @Column(length = Columns.msgContent, nullable = true)
    private String msgContent;
}
