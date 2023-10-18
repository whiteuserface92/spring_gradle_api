package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BasePushLog;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.PushLog)
public class PushLog extends BasePushLog {

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "push_request_id", nullable = false)
    private PushRequest pushRequest;
}
