package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 7:05 오후
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
