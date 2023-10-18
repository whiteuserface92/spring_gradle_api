package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.basemodel.Columns;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BasePushLog extends BasePersistable {

    /**
     * 발송일시
     */
    private LocalDateTime sendedOn;

    /**
     * 수신일시
     */
    private LocalDateTime receivedOn;

    /**
     * 읽음일시
     */
    private LocalDateTime readOn;

    /**
     * 삭제일시
     */
    private LocalDateTime deletedOn;

    /**
     * FCM 결과메시지
     */
    @Column(length = Columns.result)
    private String result;
}
