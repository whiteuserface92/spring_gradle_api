package com.dlsdlworld.spring.api.param;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
public class AppUploadHistoryParam {
    @NotEmpty
    private String appKind;

    @NotEmpty
    private String fileCode;

    @NotEmpty
    private String userId;

    private String fileVersion;
}
