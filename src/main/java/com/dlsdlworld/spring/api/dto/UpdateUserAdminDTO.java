package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
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
