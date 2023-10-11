package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.event.UserConfigListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer ricky
 * Date 2020/01/12
 * Time 7:06 오후
 */
@Getter
@Setter
@Entity
@EntityListeners(UserConfigListener.class)
@Table(name = Tables.UserConfig)
public class UserConfig extends BaseUserConfig {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
