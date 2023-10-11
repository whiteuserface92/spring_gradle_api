package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 1:11 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseOauthApprovals extends BasePersistable {

    private String userId;

    private String clientId;

    private String scope;

    private String status;

    private LocalDateTime expiresAt;

    private LocalDateTime lastModifiedAt;
}
