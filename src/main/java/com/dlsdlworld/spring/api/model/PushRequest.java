package com.dlsdlworld.spring.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.PushRequest)
public class PushRequest extends BasePushRequest {

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "push_message_id", nullable = false)
    private PushMessage pushMessage;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    private App app;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "pushRequest", cascade = CascadeType.REMOVE)
    private PushLog pushLog;
}
