package com.dlsdlworld.spring.api.basemodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Client Javascript logs
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseClientLog extends BasePersistable {
    /**
       10.toNative , 20.callback ,30.Native App call,  99.js Error
     */
    @Column(name = "client_cd", nullable = false, length = 10)
    private String clientCd;

    @Column(name = "func_call", nullable = false, length = 256)
    private String funcCall;

    @Lob
    @Column(name = "args_param")
    @Type(type = "org.hibernate.type.TextType")
    private String argsParam;

    @Lob
    @Column(name = "log_data")
    @Type(type = "org.hibernate.type.TextType")
    private String logData;

    @Column(name = "elapsed_time")
    private Integer elapsedTime;

    @Column(name = "trace_id", length = 36)
    private String traceId;

    @Column(name = "user_ip", nullable = false, length = 40)
    private String userIp;

    @Column(name = "user_accnt", length = 50)
    private String userAccnt;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_on", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
}
