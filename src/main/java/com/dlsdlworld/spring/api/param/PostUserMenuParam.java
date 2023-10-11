package com.dlsdlworld.spring.api.param;

import com.dlsdlworld.spring.api.types.MenuTypes;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

/**
 */
@Data
public class PostUserMenuParam {

    /**사용자 id*/
//    @NotEmpty
    private Long userId;

    /** 메뉴 ID */
//    @NotEmpty
    private Long menuId;

    /**메뉴유형 */
//    @NotEmpty
    @Enumerated(EnumType.STRING)
    private MenuTypes menuType;

    /**노드레벨 */
    private Short level;

    /**표시순서 */
    @NotEmpty
    private String dispOrd;

    /**재정의메시지코드 */
    private String ovrdMsgCd;

    /**재정의서비스주소 */
    private String ovrdServiceUrl;

    /**재정의이미지조수 */
    private String ovrdImgUrl;

}
