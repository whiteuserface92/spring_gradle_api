package com.dlsdlworld.spring.api.param;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserAccessHistoryParam {

    Long id;

    Long userId;

    String myCi;

    String accessType;

    String accessTarget;

    String accessDesc;

    String hospitalCd;

    Long createdBy;

    String createdOn;

}
