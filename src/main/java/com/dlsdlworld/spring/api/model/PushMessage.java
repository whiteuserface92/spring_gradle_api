package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

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
@Table(name = Tables.PushMessage)
public class PushMessage extends BasePushMessage {

    @OneToMany(mappedBy = "pushMessage", cascade =  CascadeType.REMOVE)
    private Set<PushRequest> pushRequests;
}
