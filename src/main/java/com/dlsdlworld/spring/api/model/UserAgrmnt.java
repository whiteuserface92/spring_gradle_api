package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseUserAgrmnt;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = "user_agrmnt")
public class UserAgrmnt extends BaseUserAgrmnt {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "terms_id", nullable = false)
    private Terms terms;
}
