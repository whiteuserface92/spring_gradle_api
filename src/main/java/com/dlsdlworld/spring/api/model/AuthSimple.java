package com.dlsdlworld.spring.api.model;

/**
 */

import com.dlsdlworld.spring.api.basemodel.BaseAuthSimple;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = Tables.AuthSimple)
public class AuthSimple extends BaseAuthSimple {

    /**
     * 사용자 인증
     */
    @ManyToOne
    @JoinColumn(name = "user_auth_id", nullable = false)
    private UserAuth userAuth;


    /**
     * 사용자디바이스
     */
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;
}
