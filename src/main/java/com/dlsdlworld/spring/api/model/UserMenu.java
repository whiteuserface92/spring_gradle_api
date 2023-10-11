package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : hansik.shin
 * Date : 2020/04/26
 * Time : 4:26 오후
 */
@Getter
@Setter
@Entity
@Table(name = Tables.UserMenu)
public class UserMenu extends BaseUserMenu {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "u_menu_id", nullable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "up_u_menu_id")
    private UserMenu parent;

    @OrderBy("disp_ord ASC")
    @OneToMany(mappedBy = "parent")
    private List<UserMenu> children;
}