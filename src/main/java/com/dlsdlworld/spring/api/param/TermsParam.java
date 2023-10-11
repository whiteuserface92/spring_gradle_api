package com.dlsdlworld.spring.api.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class TermsParam {

    @NotEmpty
    private List<String> termsOwnerCds;
}
