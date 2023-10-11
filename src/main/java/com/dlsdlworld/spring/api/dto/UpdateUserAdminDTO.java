package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : suyeon.you
 * Date : 2020-09-09
 * Time : 오전 11:38
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserAdminDTO {

    @NotNull
    Integer level;


    private Set<Long> roleIds;

    private Set<Long> hospitalIds;


}
