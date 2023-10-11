package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDefaultPageListDTO {
    private String cd ;// "/patSrh/outPatientList","
    private String nm; // "외래","
    private String eng; //"out"
 //   private String out; // "icon-set1-1"
    private String icon; //": "icon-set1-1", "
    private Long menuId;
    private Long hospitalMenuId;
    private String dispOrd;
}
