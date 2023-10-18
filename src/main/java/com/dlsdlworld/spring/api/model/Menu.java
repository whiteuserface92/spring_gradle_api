package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseMenu;
import com.dlsdlworld.spring.api.event.MenuListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.Menu)
@EntityListeners(MenuListener.class)
public class Menu extends BaseMenu {

    /**
     * 사용자 메뉴(즐겨찾기)
     */
    @OneToMany(mappedBy = "menu")
    private Set<UserMenu> userMenus;

    /**
     * 병원메뉴 매핑
     */
    @OneToMany(mappedBy = "menu")
    private Set<HospitalMenu> hospitalMenus;
}
